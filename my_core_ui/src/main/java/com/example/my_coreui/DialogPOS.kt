package com.example.my_coreui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView


class DialogPOS(context: Context) : Dialog(context) {

    private var _title: TextView? = null
    private var _description: TextView? = null
    private var _tvTextPositive: TextView? = null
    private var _tvTextNegative: TextView? = null
    private var _llPositive: LinearLayout? = null
    private var _llNegative: LinearLayout? = null
    private var _etInsertText: EditText? = null
    private var _ibNegative: ImageButton? = null
    private var _ibPositive: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        context.setTheme(android.R.style.Theme_Material_Light_Dialog_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_pos)
        setupViews()
        goneAllItems()
    }

    /** * deixar baixo os Metodos que servirão para preparar o Dialog não visiveis aos usuários */
    override fun onStart() {
        super.onStart()
        this.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupViews() {
        _title = findViewById(R.id.tv_title)
        _description = findViewById(R.id.tv_description)
        _llPositive = findViewById(R.id.ll_positive)
        _llNegative = findViewById(R.id.ll_negative)
        _tvTextPositive = findViewById(R.id.tv_text_positive)
        _tvTextNegative = findViewById(R.id.tv_text_negative)
        _etInsertText = findViewById(R.id.et_insertText)
        _ibNegative = findViewById(R.id.ib_negative)
        _ibPositive = findViewById(R.id.ib_positive)
    }

    private fun goneAllItems() {
        _title?.visibility = View.GONE
        _description?.visibility = View.GONE
        _llPositive?.visibility = View.VISIBLE
        _llNegative?.visibility = View.VISIBLE
        _etInsertText?.visibility = View.GONE
    }

    /** * Deixar abaixo apenas metodos publicos que servirão para montar nosso Dialog personalizado */
    fun setTitle(title: String?) {
        _title?.visibility = View.VISIBLE
        _title?.text = title
    }

    fun setDescription(description: String?) {
        _description?.visibility = View.VISIBLE
        _description?.text = description

    }



    fun setButtonPositive(
        descriptionPositiveButton: String?,
        color: Int?, icon: Int?, okListener: View.OnClickListener?
    ) {
        _ibPositive?.let { imageButton ->
            imageButton.visibility = View.VISIBLE
            if (descriptionPositiveButton != null)
                _tvTextPositive?.text = descriptionPositiveButton
            imageButton.setOnClickListener(okListener)
            changeColorImageButton(imageButton, color)
            icon?.let {
                setIconButton(imageButton, it)
            }
        }
    }

    private fun setIconButton(imageButton: ImageButton, icon: Int) {
        imageButton.setImageResource(icon)
    }

    private fun changeColorImageButton(imageButton: ImageButton, color: Int?) {
        color?.let { colorInt ->
            context.resources?.getColor(colorInt)
                ?.let { imageButton.setBackgroundColor(it) }
        }
    }

    fun setButtonNegative(
        descriptionNegativeButton: String?, color: Int?, icon: Int?,
        cancelListener: View.OnClickListener?
    ) {
        _ibNegative?.let { imageButton ->
            imageButton.visibility = View.VISIBLE
            if (descriptionNegativeButton != null)
                _tvTextNegative?.text = descriptionNegativeButton
            imageButton.setOnClickListener(cancelListener)
            changeColorImageButton(imageButton, color)
            icon?.let {
                setIconButton(imageButton, it)
            }
        }
    }

    fun setInputNumberEditText(typeMask: TypeMask?) {
        _etInsertText?.visibility = View.VISIBLE
        _etInsertText?.inputType = InputType.TYPE_CLASS_NUMBER
        typeMask?.let {
            setMaskInEditText(it)
        }
    }

    fun setInputEditText() {
        _etInsertText?.visibility = View.VISIBLE
    }

    fun setTimeOut(delay: Long) {
        Handler().postDelayed({
            this.dismiss()
        }, delay)
    }

    /** * Metodos Privados auxiliam nas rotinas publicas */
    private fun setMaskInEditText(typeMask: TypeMask) {
        when (typeMask) { // TODO deixar esse cara mais generico futuramente
            TypeMask.DATA_WITH_BAR -> _etInsertText?.addTextChangedListener(
                _etInsertText?.let {
                    MaskUtils.mask(
                        it,
                        MaskUtils.FORMAT_HOUR
                    )
                }
            )

            TypeMask.FORMAT_DATE_WITH_DASH -> _etInsertText?.addTextChangedListener(
                _etInsertText?.let {
                    MaskUtils.mask(
                        it,
                        MaskUtils.FORMAT_DATE
                    )
                }
            )

            TypeMask.MONETARY -> _etInsertText?.addTextChangedListener(
                _etInsertText?.let {
                    MaskUtils.mask(
                        it,
                        MaskUtils.FORMAT_MONEY
                    )
                }
            )

            else -> {
            }
        }
    }
}

