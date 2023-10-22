package com.example.chat.domain


import com.gbesteban.chat.app.ErrorApp
import com.iesam.kotlintrainning.Either

class GetDogUseCase(private val repository: DogRepository) {

    operator  suspend fun invoke():Either<ErrorApp,List<Dog>>{
        return  repository.obtain()
    }
}