package com.example.my_coreui

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


object MaskUtils {
    const val FORMAT_CPF = "###.###.###-##"
    const val FORMAT_CNPJ = "##.###.###/####-##"
    const val FORMAT_FONE = "#####-####"
    const val FORMAT_CEP = "#####-###"
    const val FORMAT_MONEY = "R$ "
    const val FORMAT_DATE = "##/##/####"
    const val FORMAT_HOUR = "##:##"

    /** * Método que deve ser chamado para realizar a formatação * ediTxt.addTextChangedListener(MaskUtils.mask(ediTxt,MaskUtils.FORMAT_DATE)); * @param ediTxt * @param mask * @return  */
    fun mask(ediTxt: EditText, mask: String): TextWatcher {
        return object : TextWatcher {
            var isUpdating = false
            var old = ""
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = unmask(s.toString())
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && str.length > old.length) {
                        mascara += m
                        continue
                    }
                    mascara += try {
                        str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)
            }
        }
    }

    fun unmask(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
            .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "").replace("[ ]".toRegex(), "")
            .replace("[:]".toRegex(), "").replace("[)]".toRegex(), "")
    }
}