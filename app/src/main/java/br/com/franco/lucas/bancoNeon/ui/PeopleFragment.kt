package br.com.franco.lucas.bancoNeon.ui

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.franco.lucas.bancoNeon.R
import br.com.franco.lucas.bancoNeon.callbacks.OnClickBtnSendCallback
import br.com.franco.lucas.bancoNeon.implementation.PeopleFragmentImpl
import br.com.franco.lucas.bancoNeon.model.Person
import br.com.franco.lucas.bancoNeon.model.Transfer
import br.com.franco.lucas.bancoNeon.presenter.PeopleFragmentPresenter
import br.com.franco.lucas.bancoNeon.ui.dialogs.DialogLoading
import br.com.franco.lucas.bancoNeon.ui.dialogs.DialogSendMoney
import br.com.franco.lucas.bancoNeon.utils.Constants

/**
 * Created by lucas on 2/24/18.
 */
class PeopleFragment:Fragment(),PeopleFragmentImpl, OnClickBtnSendCallback {

    private lateinit var sendMoneyDialog : Dialog
    private lateinit var loadingDialog : Dialog

    private lateinit var presenter: PeopleFragmentPresenter
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_people,container,false)
        loadingDialog = DialogLoading(activity,getString(R.string.sending_your_money)).bind()
        presenter = PeopleFragmentPresenter()
        presenter.attachView(activity,this)
        presenter.bindRecyclerPeople(rootView.findViewById(R.id.recycler_people),
                arguments.getInt(Constants.TYPE),arguments.getParcelableArrayList<Transfer>(Constants.TRANSFERS))
        return rootView
    }

    override fun showDialog(person: Person) {
        sendMoneyDialog = DialogSendMoney(person, activity, this).bind()
        sendMoneyDialog.show()
    }

    override fun dismissDialog() {
        sendMoneyDialog.dismiss()
    }

    override fun showToast(message: String) {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }

    override fun onClick(id: Long, value: Double) {
        presenter.onClickBtnSend(id,value)
    }

    override fun showLoading() {
        loadingDialog.show()

    }

    override fun hideLoading() {
        loadingDialog.hide()
    }

}
