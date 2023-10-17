package com.example.burguer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chat.domain.Dog
import com.example.chat.domain.GetDogUseCase
import com.example.chat.domain.SaveDogUseCase

import com.gbesteban.chat.app.ErrorApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainModelView (
    private val saveDogUseCase: SaveDogUseCase,
    private val getDogUseCase: GetDogUseCase
):ViewModel(){

    private val  _uiState= MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveDog(name:String,description:String,sex:String,date:String ){
        saveDogUseCase(name,description,sex,date).fold(
            { responseError(it) },
            { responseSuccess(it) }
        )
    }

    fun loadDog(){
        viewModelScope.launch(Dispatchers.IO) {
            getDogUseCase().fold(
                { responseError(it) },
                { responseGetUserSuccess(it) }
            )
        }
    }
    //Errores
    private fun responseError(errorApp: ErrorApp) {

    }

    private fun responseSuccess(isOk: Boolean) {

    }

    private fun responseGetUserSuccess(dog: Dog) {
        _uiState.postValue(UiState(dog = dog))
    }
    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val dog: Dog? = null
    )
}