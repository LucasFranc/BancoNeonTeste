package br.com.franco.lucas.bancoNeon.repository

import br.com.franco.lucas.bancoNeon.utils.Constants
import br.com.franco.lucas.bancoNeon.callbacks.OnResponseCallback
import br.com.franco.lucas.bancoNeon.services.GenerateTokenService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by lucas on 2/21/18.
 */
class GenerateTokenRepository {

    fun generateToken(callback : OnResponseCallback){
        val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(GenerateTokenService::class.java)
        service.generateToken("Lucas Franco","lucasfranco1795@gmail.com")
                .enqueue(object : Callback<String>{
                    override fun onResponse(call: Call<String>?, response: Response<String>?) {
                        if(response!!.isSuccessful)
                            callback.onResponse(response.body()!!)
                        else
                            callback.onError(response.code(),response.message()) // aqui o servidor
                        // poderia retornar mensagens de erro mais direcionadas ao erro em si.
                    }
                    override fun onFailure(call: Call<String>?, t: Throwable?) {
                        callback.onError(500,null) // Aqui poderia vir algum
                        // código padrão para falta de internet, ou outros erros mais especificos, ao invés de somente 500.
                    }
                })
    }


}