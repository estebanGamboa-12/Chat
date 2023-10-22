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

    override suspend fun obtain(): Either<ErrorApp, List<Dog>> {
        var chat=localDataSource.getDog()
        chat.mapLeft {
            return apiMockRemoteDataSource.getChat().map {
                localDataSource.save(it)
                it
            }
        }
              return apiMockRemoteDataSource.getChat().map {
                localDataSource.save(it)
                it
            }
        }

}

