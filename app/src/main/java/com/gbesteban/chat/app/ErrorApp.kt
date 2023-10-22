package com.gbesteban.chat.app

sealed class ErrorApp{
    object DataError : ErrorApp()
    object NetworkError : ErrorApp()
    object UnknowError : ErrorApp()
}


