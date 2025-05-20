package com.example.testapp.apis

sealed class ResponseStates<out R> {
    // Can be used where we have api call on button click
    data object Init : ResponseStates<Nothing>()
    data object Loading : ResponseStates<Nothing>()
    data class Success<out T>(val data: T) : ResponseStates<T>()
    data class Error(val error: Exception) : ResponseStates<Nothing>()
}