package br.com.franco.lucas.bancoNeon.implementation

import br.com.franco.lucas.bancoNeon.model.Transfer

/**
 * Created by lucas on 2/26/18.
 */
interface HistoryActivityImpl {

    fun onResponseSuccess(transfers:ArrayList<Transfer>)
    fun showLoading()
    fun hideLoading()

}