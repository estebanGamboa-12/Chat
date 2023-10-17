package com.gbesteban.chat.data


import com.example.chat.domain.Dog
import com.example.chat.domain.DogRepository
import com.gbesteban.chat.app.ErrorApp
import com.gbesteban.chat.data.local.XmlLocalDataSource
import com.gbesteban.chat.data.remote.ApiMockRemoteDataSource
import com.iesam.kotlintrainning.Either

class DogDataRepository(
    private val localDataSource: XmlLocalDataSource,
    private val apiMockRemoteDataSource: ApiMockRemoteDataSource
): DogRepository {
    override fun save(name:String,  description:String, sex:String, date:String): Either<ErrorApp, Boolean> {
        return localDataSource.saveDog(name,description,sex,date)
    }
    override fun obtain(): Either<ErrorApp, Dog> {
        return apiMockRemoteDataSource.getDog()
    }
}