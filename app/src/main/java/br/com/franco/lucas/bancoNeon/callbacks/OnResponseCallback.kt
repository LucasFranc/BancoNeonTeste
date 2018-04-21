package br.com.franco.lucas.bancoNeon.callbacks

/**
 * Created by lucas on 2/23/18.
 */
interface OnResponseCallback {

    fun onResponse(response: Any)
    fun onError(errorCode: Int, errorMessage: String?)

}