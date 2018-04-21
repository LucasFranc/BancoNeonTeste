package br.com.franco.lucas.bancoNeon.implementation

import br.com.franco.lucas.bancoNeon.model.Person

/**
 * Created by lucas on 2/24/18.
 */
interface PeopleFragmentImpl {

    fun showDialog(person:Person)
    fun dismissDialog()
    fun showToast(message:String)
    fun showLoading()
    fun hideLoading()
}