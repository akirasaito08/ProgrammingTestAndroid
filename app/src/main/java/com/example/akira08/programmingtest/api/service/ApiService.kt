package com.example.akira08.programmingtest.api.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private lateinit var retrofit: Retrofit
    private val BASE_URL = "https://simple-contact-crud.herokuapp.com/"

    val retrofitInstance: Retrofit
        get() {
            retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
}