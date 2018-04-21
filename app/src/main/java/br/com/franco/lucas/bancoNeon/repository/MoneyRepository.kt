package br.com.franco.lucas.bancoNeon.repository

import android.app.Activity
import br.com.franco.lucas.bancoNeon.callbacks.OnResponseCallback
import br.com.franco.lucas.bancoNeon.model.AccessToken
import br.com.franco.lucas.bancoNeon.model.SendMoney
import br.com.franco.lucas.bancoNeon.model.Transfer
import br.com.franco.lucas.bancoNeon.services.MoneyService
import br.com.franco.lucas.bancoNeon.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by lucas on 2/25/18.
 */
class MoneyRepository {

    fun sendMoney(sendMoney:SendMoney,callback : OnResponseCallback){
        val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(MoneyService::class.java)
        service.sendMoney(sendMoney)
                .enqueue(object : Callback<Boolean> {
                    override fun onResponse(call: Call<Boolean>?, response: Response<Boolean>?) {
                        if(response!!.isSuccessful)
                            callback.onResponse(response.body()!!)
                        else
                            callback.onError(response.code(),response.message()) // aqui o servidor
                        // poderia retornar mensagens de erro mais direcionadas ao erro em si.
                    }
                    override fun onFailure(call: Call<Boolean>?, t: Throwable?) {
                        callback.onError(500,null) // Aqui poderia vir algum
                        // código padrão para falta de internet, ou outros erros mais especificos, ao invés de somente 500.
                    }
                })
    }

    fun getTransfers(activity: Activity, callback : OnResponseCallback){
        val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(MoneyService::class.java)
        service.getTransfers(AccessToken().loadToken(activity))
                .enqueue(object : Callback<ArrayList<Transfer>> {
                    override fun onResponse(call: Call<ArrayList<Transfer>>?, response: Response<ArrayList<Transfer>>?) {
                        if(response!!.isSuccessful)
                            callback.onResponse(response.body()!!)
                        else
                            callback.onError(response.code(),response.message()) // aqui o servidor
                        // poderia retornar mensagens de erro mais direcionadas ao erro em si.
                    }
                    override fun onFailure(call: Call<ArrayList<Transfer>>?, t: Throwable?) {
                        callback.onError(500,null) // Aqui poderia vir algum
                        // código padrão para falta de internet, ou outros erros mais especificos, ao invés de somente 500.
                    }
                })
    }

}