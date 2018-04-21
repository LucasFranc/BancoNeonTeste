package br.com.franco.lucas.bancoNeon.presenter

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.franco.lucas.bancoNeon.R
import br.com.franco.lucas.bancoNeon.callbacks.OnClickCallback
import br.com.franco.lucas.bancoNeon.callbacks.OnResponseCallback
import br.com.franco.lucas.bancoNeon.implementation.PeopleFragmentImpl
import br.com.franco.lucas.bancoNeon.model.AccessToken
import br.com.franco.lucas.bancoNeon.model.Person
import br.com.franco.lucas.bancoNeon.model.SendMoney
import br.com.franco.lucas.bancoNeon.model.Transfer
import br.com.franco.lucas.bancoNeon.repository.MoneyRepository
import br.com.franco.lucas.bancoNeon.ui.adapters.SendMoneyAdapter
import br.com.franco.lucas.bancoNeon.ui.adapters.TransfersAdapter
import br.com.franco.lucas.bancoNeon.utils.Constants
import br.com.franco.lucas.bancoNeon.utils.ErrorManager
import java.util.ArrayList

/**
 * Created by lucas on 2/24/18.
 */
class PeopleFragmentPresenter : OnClickCallback, OnResponseCallback {

    private lateinit var activity : Activity
    private lateinit var impl : PeopleFragmentImpl

    fun attachView(activity: Activity, impl: PeopleFragmentImpl) {
        this.activity = activity
        this.impl = impl
    }
    fun bindRecyclerPeople(recyclerPeople: RecyclerView, type: Int, transfers: ArrayList<Transfer>?){
        recyclerPeople.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        if(type == Constants.TYPE_SEND_MONEY)
            recyclerPeople.adapter = SendMoneyAdapter(activity, Person.returnMockedData(), this)
        else {
            recyclerPeople.adapter = TransfersAdapter(activity,transfers!!)
        }
    }

    override fun onClick(person: Person) {
        impl.showDialog(person)
    }

    fun onClickBtnSend(id:Long, value: Double) {
        if(value.toString() != (Constants.ZERO_DOUBLE)) {
            val repository = MoneyRepository()
            impl.showLoading()
            repository.sendMoney(SendMoney(id, AccessToken().loadToken(activity), value), this)
        }else{
            impl.showToast(activity.getString(R.string.the_value_must_be))
        }
    }

    override fun onResponse(response: Any) {
        impl.hideLoading()
        impl.dismissDialog()
        impl.showToast(activity.getString(R.string.money_sent_with_success))
    }

    override fun onError(errorCode: Int, errorMessage: String?) {
        impl.hideLoading()
        impl.dismissDialog()
        val v : View = activity.findViewById(R.id.recycler_people)
        ErrorManager().manageError(v,errorCode,errorMessage,null)
    }

}
