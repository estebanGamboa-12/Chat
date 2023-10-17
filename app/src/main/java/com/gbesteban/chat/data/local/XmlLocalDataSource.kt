package com.gbesteban.chat.data.local

import android.content.Context
import com.example.chat.domain.Dog

import com.gbesteban.chat.app.ErrorApp
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right

class XmlLocalDataSource(private val context:Context) {
    private val sharedPref = context.getSharedPreferences("Dogs", Context.MODE_PRIVATE)

    fun saveDog(
        titulo: String,
        texto: String,
        hora: String,
        mensajes: String
    ): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()){
                putInt("id", (1..100).random())
                putString("namr", titulo)
                putString("description", texto)
                putString("sex", hora)
                putString("date", mensajes)
                apply()
            }
            true.right()
        }catch (ex:Exception){
            ErrorApp.UnkowError.left()
        }

    }
    fun findDog(): Either<ErrorApp, Dog> {
        return try {
            Dog(
                sharedPref.getInt("id",0),
                sharedPref.getString("name", "")!!,
                sharedPref.getString("description", "")!!,
                sharedPref.getString("sex", "")!!,
                sharedPref.getString("date", "")!!
            ).right()
        }catch (ex:Exception){
            return ErrorApp.UnkowError.left()
        }
    }
}