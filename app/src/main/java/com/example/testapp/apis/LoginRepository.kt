package com.example.testapp.apis

import com.example.testapp.model.LoginResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor() {

    fun callLoginApi(userName: String, password: String) : Flow<ResponseStates<LoginResponse>> {
        return flow {
            try {
                delay(2000)
                emit(ResponseStates.Success(LoginResponse(true,"Login Successful"))                 )
            }catch (e:Exception) {
               emit(ResponseStates.Error(e))
            }
        }
    }
}