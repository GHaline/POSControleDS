package com.example.poscontroleds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        Handler().postDelayed(
            { // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                val i = Intent(
                    this@Splash,
                    MainActivity::class.java
                )
                startActivity(i)

                // Fecha esta activity
                finish()
            }, SPLASH_TIME_OUT)

        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    companion object {
        private const val SPLASH_TIME_OUT = 5000L
    }
}
