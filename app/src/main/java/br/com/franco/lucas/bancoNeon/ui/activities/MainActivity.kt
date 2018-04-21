package br.com.franco.lucas.bancoNeon.ui.activities

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.franco.lucas.bancoNeon.R
import br.com.franco.lucas.bancoNeon.implementation.MainActivityImpl
import br.com.franco.lucas.bancoNeon.presenter.MainActivityPresenter
import br.com.franco.lucas.bancoNeon.ui.dialogs.DialogLoading
import br.com.franco.lucas.bancoNeon.utils.TransactionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityImpl {
    private var presenter : MainActivityPresenter = MainActivityPresenter()
    private lateinit var loadingDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingDialog = DialogLoading(this,getString(R.string.loading_your_section)).bind()
        presenter.attachView(this,this)
        presenter.doGenerateTokenRequest()
        presenter.bindBtns(btn_send_money,btn_history)
    }

    override fun startSendMoneyActivity() {
        TransactionManager().activityTransaction(this, SendMoneyActivity::class.java)
    }

    override fun startHistoricActivity() {
        TransactionManager().activityTransaction(this, HistoricActivity::class.java)
    }

    override fun showLoading() {
        loadingDialog.show()
    }

    override fun hideLoading() {
        loadingDialog.hide()
    }

}
