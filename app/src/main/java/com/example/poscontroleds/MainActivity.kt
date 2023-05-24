package com.example.poscontroleds


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView


class MainActivity : AppCompatActivity(){

    private var cardDialog: CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        setupViews()
    }

    private fun setupViews() {
        cardDialog = findViewById(R.id.card_dialog)
        cardDialog?.setOnClickListener {
            val i = Intent(
                this@MainActivity,
                Components::class.java
            )
            startActivity(i)
        }
    }

}