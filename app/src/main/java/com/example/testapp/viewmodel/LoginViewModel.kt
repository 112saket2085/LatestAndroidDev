package com.example.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.apis.LoginRepository
import com.example.testapp.apis.ResponseStates
import com.example.testapp.model.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    private val _loginStateFlow  = MutableStateFlow<ResponseStates<LoginResponse>>(ResponseStates.Loading)
    val loginStateFlow = _loginStateFlow.asStateFlow()

    fun makeLoginApiCall(userName: String, password: String) {
        viewModelScope.launch {
            _loginStateFlow.emit(ResponseStates.Loading)
             val data = repository.callLoginApi(userName,password)
             data.collectLatest{
                 _loginStateFlow.emit(it)
             }
        }
    }
}