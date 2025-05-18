package com.example.testapp.apis

sealed class ResponseStates<out R> {
    data object Loading : ResponseStates<Nothing>()
    data class Success<out T>(val data: T) : ResponseStates<T>()
    data class Error(val error: Exception) : ResponseStates<Nothing>()
}