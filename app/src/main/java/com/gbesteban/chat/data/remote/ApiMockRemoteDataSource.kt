package com.gbesteban.chat.data.remote

import com.example.chat.domain.Dog
import com.gbesteban.chat.app.ErrorApp
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.right

class ApiMockRemoteDataSource() {

    fun getDog():Either<ErrorApp, Dog>{
        return Dog(0,"Burguer 85 ","22-30","-17","98").right()
    }
}