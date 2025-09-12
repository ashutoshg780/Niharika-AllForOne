package com.example.niharika_all_for_one

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Payouts_Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var RoleTitle: TextView
    private lateinit var LogOut: ImageView
    private lateinit var Back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payouts)

        auth = FirebaseAuth.getInstance()
        RoleTitle = findViewById(R.id.profileRole)

        checkUser()

        // Initialize buttons
        LogOut = findViewById(R.id.logoutButton)
        Back = findViewById(R.id.backButton)

        //Logouts the page and opens start page
        LogOut.setOnClickListener {
            // Clear Firebase session
            FirebaseAuth.getInstance().signOut()

            // Clear SharedPreferences (stored phone number)
            getSharedPreferences("UserPrefs", MODE_PRIVATE).edit().clear().apply()

            // Navigate to Start Screen
            val intent = Intent(this, Start_Screen_Activity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Back Button
        Back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // This will behave like system back
        }

    }

    private fun checkUser() {
        // Get phone number from SharedPreferences
        val phoneNumber = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            .getString("phone", null)

        if (phoneNumber.isNullOrEmpty()) {
            Toast.makeText(this, "Phone number not available!", Toast.LENGTH_SHORT).show()
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, Start_Screen_Activity::class.java))
            finish()
            return
        }

        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("Users").document(phoneNumber)

        userRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val role = document.getString("role")
                    val name = document.getString("fullName")
                    RoleTitle.text = "$role: $name"
                } else {
                    Toast.makeText(this, "User data not found!", Toast.LENGTH_SHORT).show()
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, Start_Screen_Activity::class.java))
                    finish()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to fetch user data!", Toast.LENGTH_SHORT).show()
                Log.e("DashboardError", "Firestore error: ${it.message}")
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, Start_Screen_Activity::class.java))
                finish()
            }
    }
}
