package com.example.testapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.viewmodel.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapp.apis.ResponseStates
import com.example.testapp.model.LoginResponse

@Composable
fun LoginScreen(
    factory: ViewModelProvider.Factory,
    modifier: Modifier,
    viewModel: LoginViewModel = viewModel(factory = factory)
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState = viewModel.loginStateFlow.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Handle login logic here
                println("Login attempt with Username: $username, Password: $password")
                viewModel.makeLoginApiCall(
                    userName = username,
                    password = password
                )
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
        HandleResponse(response = loginState.value)
    }
}

@Composable
fun HandleResponse(response: ResponseStates<LoginResponse>) {
    when(response) {
       is ResponseStates.Loading -> Text("Loading....")
       is ResponseStates.Success -> Text(response.data.message)
       is ResponseStates.Error -> Text(response.error.message.toString())
    }
}