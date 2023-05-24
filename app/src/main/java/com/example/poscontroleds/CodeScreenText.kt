package com.example.poscontroleds

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.widget.Toast
import androidx.core.text.color
import androidx.core.text.scale

class CodeScreenText : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_screen_text)

        fun Context.toast(message: CharSequence) =
            Toast.makeText(this, "DEU CERTO !!!!", Toast.LENGTH_SHORT).show()
    }
}