package com.gbesteban.chat.data.remote

import com.example.chat.domain.Dog
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlinx.coroutines.launch

interface ApiService {
    @GET("whatsapp-view.json") //
    suspend fun getItems(): Response<List<Dog>>
}


