package com.example.niharika_all_for_one

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Spare_Parts_Order_History_Activity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var headerRole: TextView
    private lateinit var orderHistoryTable: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spare_parts_order_history)

        // === Initialize UI elements ===
        backButton = findViewById(R.id.backButton)
        headerRole = findViewById(R.id.profileRole)
        orderHistoryTable = findViewById(R.id.orderHistoryTable)

        // === Back button functionality ===
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // === Load order history from Firestore ===
        loadOrderHistory()
    }

    // === Load documents from Spare_Parts_Order_History collection ===
    private fun loadOrderHistory() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Spare_Parts_Order_History")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val itemCode = document.getString("itemCode") ?: ""
                    val itemName = document.getString("itemName") ?: ""
                    val orderedQty = document.getString("orderedQty") ?: ""
                    val status = document.getString("status") ?: ""
                    val orderedOn = document.getString("orderedOn") ?: ""

                    val row = TableRow(this)
                    row.addView(makeTextView(itemCode))
                    row.addView(makeTextView(itemName))
                    row.addView(makeTextView(orderedQty))
                    row.addView(makeTextView(status))
                    row.addView(makeTextView(orderedOn))
                    orderHistoryTable.addView(row)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to fetch order history", Toast.LENGTH_SHORT).show()
            }
    }

    // === Helper: Create TextView for a table cell ===
    private fun makeTextView(value: String): TextView {
        return TextView(this).apply {
            text = value
            setPadding(8, 4, 8, 4)
            textSize = 14f
        }
    }
}
