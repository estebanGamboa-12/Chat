package com.gbesteban.chat.data.remote


import com.example.chat.domain.Dog
import com.gbesteban.chat.app.ErrorApp
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class ApiMockRemoteDataSource() {
    suspend fun getChat(): Either<ErrorApp, List<Dog>> {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dam.sitehub.es/curso-2023-2024/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(ApiService::class.java)

            val response = service.getItems()

            if (response.isSuccessful) {
                val itemsList = response.body()
                if (itemsList != null && itemsList.isNotEmpty()) {
                    return itemsList.right()
                } else {
                    // Manejar el caso en el que la lista está vacía
                    return ErrorApp.DataError.left()
                }
            } else {
                // Manejar el caso en el que la respuesta no es exitosa
                return ErrorApp.NetworkError.left()
            }
        } catch (e: Exception) {
            // Manejar errores de red u otros errores
            return ErrorApp.NetworkError.left()
        }
    }
}

