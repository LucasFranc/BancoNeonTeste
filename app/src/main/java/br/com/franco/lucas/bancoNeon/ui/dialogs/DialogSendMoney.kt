package br.com.franco.lucas.bancoNeon.ui.dialogs

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import br.com.franco.lucas.bancoNeon.R
import br.com.franco.lucas.bancoNeon.callbacks.OnClickBtnSendCallback
import br.com.franco.lucas.bancoNeon.model.Person
import com.blackcat.currencyedittext.CurrencyEditText
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_send_money.*
import java.util.*

/**
 * Created by lucas on 2/25/18.
 */
class DialogSendMoney(private val person: Person,
                      private val activity:Activity,
                      private val callback:OnClickBtnSendCallback){

    fun bind() : Dialog{
        val dialog = Dialog(activity, R.style.ThemeDialog)
        initDialog(dialog)
        bindItens(dialog)
        return dialog
    }

    private fun initDialog(dialog : Dialog) {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_send_money)
    }

    private fun bindItens(dialog : Dialog) {
        dialog.profile_name.text = person.name
        dialog.profile_email.text = person.phone
        Picasso.with(activity).load(person.avatar).into(dialog.profile_avatar,object : Callback {
            override fun onSuccess() { dialog.progress.visibility = View.GONE }
            override fun onError() { dialog.progress.visibility = View.VISIBLE }
        })
        val edtValue :CurrencyEditText = dialog.edt_value
        edtValue.defaultLocale = Locale("pt-BR")
        edtValue.setOnClickListener {edtValue.setSelection(edtValue.text.length) }
        dialog.close_dialog.setOnClickListener({dialog.dismiss()})
        dialog.btn_send.setOnClickListener({
            val value = edtValue.text.toString()
            callback.onClick(person.id,formatValue(value).toDouble())
        })
    }

    private fun formatValue(value: String): String {
        var value1 = value
        value1 = value1.replace(",", ".")
        value1 = value1.replace(".", "")
        if (value1.contains("R$"))
            value1 = value1.replace("R$", "")
        else
            value1 = value1.replace("$", "")
        value1 = value1.substring(0,value1.length - 2) + "." + value1.substring(value1.length - 2, value1.length)
        return value1
    }

}