package br.com.franco.lucas.bancoNeon.ui.activities

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import br.com.franco.lucas.bancoNeon.R
import br.com.franco.lucas.bancoNeon.implementation.HistoryActivityImpl
import br.com.franco.lucas.bancoNeon.model.Transfer
import br.com.franco.lucas.bancoNeon.presenter.HistoricActivityPresenter
import br.com.franco.lucas.bancoNeon.ui.dialogs.DialogLoading
import kotlinx.android.synthetic.main.activity_historic.*

/**
 * Created by lucas on 2/24/18.
 */
class HistoricActivity : AppCompatActivity(), HistoryActivityImpl {

    private lateinit var presenter: HistoricActivityPresenter
    private lateinit var loadingDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historic)
        bindToolbar()
        loadingDialog = DialogLoading(this,getString(R.string.searching_where)).bind()
        presenter = HistoricActivityPresenter()
        presenter.attachView(this,this)
        presenter.doRequestGetTransfers()
    }

    private fun bindToolbar() {
            findViewById<AppCompatTextView>(R.id.toolbar_title).text = resources.getText(R.string.deposit_history)
            findViewById<AppCompatImageView>(R.id.back_arrow).setOnClickListener({finish()})
    }

    override fun onResponseSuccess(transfers: ArrayList<Transfer>) {
        presenter.bindFragment(transfers)
        presenter.bindChart(recycler_chart,transfers)
    }

    override fun showLoading() {
        loadingDialog.show()
    }

    override fun hideLoading() {
        loadingDialog.hide()
    }

}
