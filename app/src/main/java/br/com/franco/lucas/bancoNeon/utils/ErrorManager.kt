package br.com.franco.lucas.bancoNeon.utils

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View
import br.com.franco.lucas.bancoNeon.R

import br.com.franco.lucas.bancoNeon.callbacks.OnRetryClickCallback

/**
 * Created by lucas on 2/23/18.
 */

class ErrorManager {

    public fun manageError(a: Activity, httpErrorCode: Int, errorMessage: String?, callBack: OnRetryClickCallback?) {
        manageError(a.currentFocus, httpErrorCode, errorMessage, callBack)
    }

    public fun manageError(v: View?, httpErrorCode: Int, errorMessage: String?, callBack: OnRetryClickCallback?) {

        var errorMessage = errorMessage

        if (errorMessage == null) {
            when (httpErrorCode) {
                400 -> { // BAD REQUEST
                    errorMessage = "Erro de requisição."
                }
                401 -> { // NÃO AUTORIZADO
                    errorMessage = "Não autorizado."
                }
                403 -> { // FORBIDDEN
                    errorMessage = "Proibido."
                }
                404 -> { //NOT FOUND
                    errorMessage = "Não encontrado."
                }
                408 -> { //TIMEOUT
                    errorMessage = "Verifique sua conexão."
                }
                409 -> { // CONFLITO
                    errorMessage = "Conflito."
                }
                413 -> { // PAYLOAD TOO LARGE
                    errorMessage = "Resposta muito grande."
                }
                422 -> { // ENTIDADE IMPROCESSÁVEL
                    errorMessage = "Erro de entidade improcessável"
                }
                500 -> { // SERVER ERROR
                    errorMessage = "Erro de servidor"
                }
                503 -> { //SERVIÇO NÃO DISPONIVEL
                    errorMessage = "Serviço não disponível."
                }
                else -> { //SE NÃO
                    errorMessage = "Erro desconhecido."
                }
            }
        }

        if (callBack != null) {
            Snackbar.make(v!!, errorMessage, Snackbar.LENGTH_INDEFINITE).setAction(v.resources.getString(R.string.retry)) {callBack.onRetryClick()}.show()
        } else {
            Snackbar.make(v!!, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }
}
