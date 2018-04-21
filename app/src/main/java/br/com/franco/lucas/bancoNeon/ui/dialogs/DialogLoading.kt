package br.com.franco.lucas.bancoNeon.ui.dialogs

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import br.com.franco.lucas.bancoNeon.R
import kotlinx.android.synthetic.main.dialog_loading.*

/**
 * Created by lucas on 2/28/18.
 */
class DialogLoading(val activity:Activity,
                    val message:String) {

    fun bind() : Dialog {
        val dialog = Dialog(activity, R.style.ThemeDialog)
        initDialog(dialog)
        bindItens(dialog)
        return dialog
    }

    private fun initDialog(dialog : Dialog) {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_loading)
    }

    private fun bindItens(dialog : Dialog) {
        dialog.pacman_view.show()
        dialog.message.text = message
    }
}