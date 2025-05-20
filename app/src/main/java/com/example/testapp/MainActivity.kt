package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.testapp.screen.LoginScreen
import com.example.testapp.ui.theme.TestAppTheme
import dagger.android.AndroidInjection
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: Provider<ViewModelProvider.Factory>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            TestAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(factory = viewModelFactory.get(), modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestAppTheme {
        LoginScreen(factory = viewModelFactory {  }, modifier = Modifier.padding(16.dp))
    }
}