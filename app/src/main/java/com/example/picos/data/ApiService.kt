package com.example.picos.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {
    @POST("predict")
    fun predict(@Body request: PredictionRequest): Call<PredictionResponse>
}