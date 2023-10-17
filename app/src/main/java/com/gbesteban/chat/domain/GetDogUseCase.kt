package com.example.chat.domain


import com.gbesteban.chat.app.ErrorApp
import com.iesam.kotlintrainning.Either

class GetDogUseCase(private val repository: DogRepository) {

    operator  fun invoke():Either<ErrorApp,Dog>{
        return  repository.obtain()
    }
}