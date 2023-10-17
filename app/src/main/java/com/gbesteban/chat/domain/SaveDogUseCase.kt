package com.example.chat.domain


import com.gbesteban.chat.app.ErrorApp
import com.iesam.kotlintrainning.Either

class SaveDogUseCase(private val repository: DogRepository) {

    operator fun invoke(name:String,description:String,sex:String,date:String):Either<ErrorApp,Boolean>{
        return repository.save(name, description, sex, date)
    }
}