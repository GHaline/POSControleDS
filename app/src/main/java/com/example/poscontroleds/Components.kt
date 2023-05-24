package com.example.poscontroleds

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.my_coreui.DialogPOS
import com.example.my_coreui.TypeMask


class Components : AppCompatActivity() {

    private var card_xml: CardView? = null
    private var card_activity: CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_components)
        setupViews()


        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            val dialogPagamento = DialogPOS(this)
            dialogPagamento.show()
            dialogPagamento.setTitle("Titulo")
            dialogPagamento.setDescription("Descrição")
            dialogPagamento.setInputNumberEditText(TypeMask.DATA_WITH_BAR)
            dialogPagamento.setButtonNegative(
                null, R.color.botao_negativo, R.drawable.ic_close
            )
            {
                Toast.makeText(this, "NEGATIVO", Toast.LENGTH_SHORT).show()
                dialogPagamento.dismiss()
            }
            dialogPagamento.setButtonPositive(null, R.color.botao_positivo, R.drawable.ic_check)
            {
                Toast.makeText(this, "POSITIVO", Toast.LENGTH_SHORT).show()
                dialogPagamento.dismiss()
            }
        }
    }

    //setPositiveButtonIcon(resources.getDrawable(android.R.drawable.ic_menu_call, theme))
    //            setIcon(resources.getDrawable(android.R.drawable.ic_dialog_alert, theme))
    private fun setupViews() {
        card_xml = findViewById(R.id.xml)
        card_xml?.visibility = View.GONE

        card_activity = findViewById(R.id.card_main)
        card_activity?.setOnClickListener {
            val i = Intent(
                this@Components,
                CodeScreenText::class.java
            )
            startActivity(i)
        }
    }

}




