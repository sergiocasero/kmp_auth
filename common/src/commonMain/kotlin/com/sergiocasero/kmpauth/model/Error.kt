package com.sergiocasero.kmpauth.model

sealed class Error {
    object NoInternet : Error()
    object NotFound : Error()
    object InvalidCredentials : Error()
    data class IO(val message: String) : Error()
    data class Default(val message: String) : Error()
}

object Success
