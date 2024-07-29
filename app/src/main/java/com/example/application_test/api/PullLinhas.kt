package com.example.application_test.api

import com.example.application_test.model.Linha
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PullLinhas {

    @POST("/Login/Autenticar")
    suspend fun authentication(@Query("token") token: String): Response<Boolean>

    @GET("/Linha/Buscar")
    suspend fun recuperarLinhas(@Query("termosBusca") linha: String): Response<List<Linha>>

}