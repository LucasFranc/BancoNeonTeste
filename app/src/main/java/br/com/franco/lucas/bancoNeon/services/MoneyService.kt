package br.com.franco.lucas.bancoNeon.services

import br.com.franco.lucas.bancoNeon.model.SendMoney
import br.com.franco.lucas.bancoNeon.model.Transfer
import br.com.franco.lucas.bancoNeon.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by lucas on 2/25/18.
 */
interface MoneyService {

    @POST(Constants.SEND_MONEY_URL)
    fun sendMoney(@Body sendMoney : SendMoney): Call<Boolean>

    @GET(Constants.GET_TRANSFERS_URL)
    fun getTransfers(
            @Query("token") token: String) : Call<ArrayList<Transfer>>
}