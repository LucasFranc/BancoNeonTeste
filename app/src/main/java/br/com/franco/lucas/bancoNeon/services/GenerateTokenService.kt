package br.com.franco.lucas.bancoNeon.services

import br.com.franco.lucas.bancoNeon.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by lucas on 2/23/18.
 */
interface GenerateTokenService{

    @GET(Constants.GENERATE_TOKEN_URL)
    fun generateToken(
            @Query("nome") name: String,
            @Query("email") email : String): Call<String>

}