package com.example.tugasintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugasintent.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRegist.setOnClickListener {
            if (binding.termsCheckbox.isChecked) {
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("EXTRA_USERNAME", binding.editUsername.text.toString())
                    putExtra("EXTRA_EMAIL", binding.editEmail.text.toString())
                    putExtra("EXTRA_PHONE", binding.editPhone.text.toString())
                    putExtra("EXTRA_PASSWORD", binding.editPassword.text.toString())
                    putExtra("EXTRA_GENDER", if (binding.radioMale.isChecked) "Male" else "Female")
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please agree to the terms and conditions", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
