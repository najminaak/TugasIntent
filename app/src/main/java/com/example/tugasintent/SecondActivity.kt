package com.example.tugasintent

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugasintent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var correctUsername: String
    private lateinit var correctPassword: String
    private lateinit var correctEmail: String
    private lateinit var correctPhone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve and store the correct username and password from the intent
        correctUsername = intent.getStringExtra("EXTRA_USERNAME") ?: ""
        correctPassword = intent.getStringExtra("EXTRA_PASSWORD") ?: ""
        correctEmail = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        correctPhone = intent.getStringExtra("EXTRA_PHONE") ?: ""

        binding.btnLogin.setOnClickListener {
            val enteredUsername = binding.editUsername.text.toString()
            val enteredPassword = binding.editPassword.text.toString()

            if (binding.termsCheckbox.isChecked) {
                if (enteredUsername == correctUsername && enteredPassword == correctPassword) {
                    // Proceed to ThirdActivity if credentials match
                    val homepageIntent = Intent(this, ThirdActivity::class.java).apply {
                        putExtra("EXTRA_USERNAME", correctUsername)
                        putExtra("EXTRA_PASSWORD", correctPassword)
                        putExtra("EXTRA_EMAIL", correctEmail)
                        putExtra("EXTRA_PHONE", correctPhone)
                        putExtra("EXTRA_GENDER", intent.getStringExtra("EXTRA_GENDER"))
                    }
                    startActivity(homepageIntent)
                } else {
                    // Show an error message or toast
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please agree to the terms and conditions", Toast.LENGTH_SHORT).show()
            }
        }
    }
}