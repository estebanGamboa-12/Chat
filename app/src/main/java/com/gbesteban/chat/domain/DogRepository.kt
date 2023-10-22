package com.example.chat.domain

import com.gbesteban.chat.app.ErrorApp
import com.iesam.kotlintrainning.Either

interface DogRepository {

    suspend fun obtain(): Either<ErrorApp, List<Dog>>
}