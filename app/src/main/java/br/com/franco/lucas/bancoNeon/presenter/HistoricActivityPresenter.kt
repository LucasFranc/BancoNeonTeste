package br.com.franco.lucas.bancoNeon.presenter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.FitWindowsFrameLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.franco.lucas.bancoNeon.R
import br.com.franco.lucas.bancoNeon.callbacks.OnResponseCallback
import br.com.franco.lucas.bancoNeon.callbacks.OnRetryClickCallback
import br.com.franco.lucas.bancoNeon.implementation.HistoryActivityImpl
import br.com.franco.lucas.bancoNeon.model.MoneySum
import br.com.franco.lucas.bancoNeon.model.Transfer
import br.com.franco.lucas.bancoNeon.repository.MoneyRepository
import br.com.franco.lucas.bancoNeon.ui.PeopleFragment
import br.com.franco.lucas.bancoNeon.ui.adapters.ChartAdapter
import br.com.franco.lucas.bancoNeon.utils.Constants
import br.com.franco.lucas.bancoNeon.utils.ErrorManager
import br.com.franco.lucas.bancoNeon.utils.TransactionManager
import java.util.*


/**
 * Created by lucas on 2/26/18.
 */
class HistoricActivityPresenter : OnResponseCallback,OnRetryClickCallback {

    lateinit var activity: AppCompatActivity
    lateinit var impl: HistoryActivityImpl

    fun attachView(activity: AppCompatActivity, impl: HistoryActivityImpl) {
        this.activity = activity
        this.impl = impl
    }

    fun bindChart(recyclerChart: RecyclerView,transfers: ArrayList<Transfer>) {
        val groupTransfers = orderList(Transfer.groupTransfers(transfers))
        recyclerChart.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        recyclerChart.adapter = ChartAdapter(activity,groupTransfers)
    }

    private fun orderList(groupTransfers: ArrayList<MoneySum>): ArrayList<MoneySum> {
        Collections.sort(groupTransfers, object : Comparator<MoneySum> {
            override fun compare(c1: MoneySum, c2: MoneySum): Int {
                return java.lang.Double.compare(c2.moneySum, c1.moneySum)
            }
        })
        return groupTransfers
    }

    fun bindFragment(transfers: ArrayList<Transfer>) {
        val fragment = PeopleFragment()
        val b = Bundle()
        b.putInt(Constants.TYPE,Constants.TYPE_HISTORIC)
        b.putParcelableArrayList(Constants.TRANSFERS,transfers)
        fragment.arguments = b
        TransactionManager().fragmentsTransaction(R.id.fragment, activity, fragment,
                Constants.PEOPLE_FRAGMENT_TAG, false)
    }

    fun doRequestGetTransfers() {
        impl.showLoading()
        val moneyRepository = MoneyRepository()
        moneyRepository.getTransfers(activity,this)
    }

    override fun onResponse(response: Any) {
        impl.hideLoading()
        val formattedTransferData = Transfer.formatTransferData(response as ArrayList<Transfer>)
        impl.onResponseSuccess(formattedTransferData)
    }

    override fun onError(errorCode: Int, errorMessage: String?) {
        impl.hideLoading()
        ErrorManager().manageError(activity.findViewById<FitWindowsFrameLayout>(R.id.fragment),
                errorCode,errorMessage,this)
    }

    override fun onRetryClick() { doRequestGetTransfers() }

}