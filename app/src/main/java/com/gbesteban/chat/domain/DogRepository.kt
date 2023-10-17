package com.example.chat.domain

import com.gbesteban.chat.app.ErrorApp
import com.iesam.kotlintrainning.Either

interface DogRepository {
    fun save( name:String,description:String,sex:String,date:String): Either<ErrorApp, Boolean>

    fun obtain(): Either<ErrorApp, Dog>
}