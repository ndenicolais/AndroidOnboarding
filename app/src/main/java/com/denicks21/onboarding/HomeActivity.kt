package com.denicks21.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var buttonTutorial: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Go to "Review"
        buttonTutorial = findViewById(R.id.btnReview)
        buttonTutorial.setOnClickListener {
            startActivity(Intent(this, ReviewActivity::class.java))
        }
    }
}