package com.example.rickapplication

import retrofit2.Call
import retrofit2.http.GET

sealed interface ApiService {
    @GET("character")
    fun getRick(): Call<ResponseRick>
}