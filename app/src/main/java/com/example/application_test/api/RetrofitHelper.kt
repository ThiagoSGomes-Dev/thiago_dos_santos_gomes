package com.example.application_test.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.olhovivo.sptrans.com.br/v2.1/")
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
    }

}