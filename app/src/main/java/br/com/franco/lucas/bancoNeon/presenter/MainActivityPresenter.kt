package br.com.franco.lucas.bancoNeon.presenter

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatButton
import android.view.View
import br.com.franco.lucas.bancoNeon.R
import br.com.franco.lucas.bancoNeon.callbacks.OnResponseCallback
import br.com.franco.lucas.bancoNeon.callbacks.OnRetryClickCallback
import br.com.franco.lucas.bancoNeon.implementation.MainActivityImpl
import br.com.franco.lucas.bancoNeon.model.AccessToken
import br.com.franco.lucas.bancoNeon.repository.GenerateTokenRepository
import br.com.franco.lucas.bancoNeon.ui.dialogs.DialogLoading
import br.com.franco.lucas.bancoNeon.utils.ErrorManager

/**
 * Created by lucas on 2/21/18.
 */
class MainActivityPresenter : OnResponseCallback, OnRetryClickCallback {

    lateinit var activity : AppCompatActivity
    lateinit var impl : MainActivityImpl

    fun attachView(activity : AppCompatActivity, impl : MainActivityImpl){
        this.activity = activity
        this.impl = impl
    }

    fun doGenerateTokenRequest() {
        impl.showLoading()
        val repository = GenerateTokenRepository()
        repository.generateToken(this)
    }

    fun bindBtns(btnSendMoney: AppCompatButton, btnHistory: AppCompatButton) {
        btnSendMoney.setOnClickListener({impl.startSendMoneyActivity()})
        btnHistory.setOnClickListener({impl.startHistoricActivity()})
    }

    override fun onResponse(response: Any) {
        impl.hideLoading()
        AccessToken().saveToken(activity, response as String)
    }

    override fun onError(errorCode: Int, errorMessage: String?) {
        impl.hideLoading()
        val v : View = activity.findViewById(R.id.btn_history)
        ErrorManager().manageError(v ,errorCode,errorMessage, this)
    }

    override fun onRetryClick() {
        doGenerateTokenRequest()
    }

}