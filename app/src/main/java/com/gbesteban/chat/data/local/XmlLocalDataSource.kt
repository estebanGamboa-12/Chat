package com.gbesteban.chat.data.local

import android.content.Context
import com.example.chat.domain.Dog


import com.gbesteban.chat.app.ErrorApp
import com.gbesteban.chat.app.serialization.JsonSerialization
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right


class XmlLocalDataSource(private val context: Context, private val serialization: JsonSerialization) {
    private val sharedPref = context.getSharedPreferences("Dogs", Context.MODE_PRIVATE)
    private val dogId1 = "1"
    private val dogId2 = "2"
    private val dogId3 = "3"

    fun getDog(): Either<ErrorApp, List<Dog>> {
        val jsonDog1 = sharedPref.getString(dogId1, null)
        val jsonDog2 = sharedPref.getString(dogId2, null)
        val jsonDog3 = sharedPref.getString(dogId3, null)

        val dogList = mutableListOf<Dog>()

        if (jsonDog1 != null) {
            dogList.add(serialization.fromJson(jsonDog1, Dog::class.java))
        }

        if (jsonDog2 != null) {
            dogList.add(serialization.fromJson(jsonDog2, Dog::class.java))
        }

        if (jsonDog3 != null) {
            dogList.add(serialization.fromJson(jsonDog3, Dog::class.java))
        }

        return if (dogList.isNotEmpty()) {
            dogList.right()
        } else {
            ErrorApp.DataError.left()
        }
    }

    fun save(dogs: List<Dog>) {
        if (dogs.size >= 3) {
            sharedPref.edit().apply {
                putString(dogId1, serialization.toJson(dogs[0], Dog::class.java))
                putString(dogId2, serialization.toJson(dogs[1], Dog::class.java))
                putString(dogId3, serialization.toJson(dogs[2], Dog::class.java))
                apply()
            }
        }
    }
}