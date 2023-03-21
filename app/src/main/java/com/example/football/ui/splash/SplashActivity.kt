package com.example.football.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.football.MainActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("----------------","splash screen")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}