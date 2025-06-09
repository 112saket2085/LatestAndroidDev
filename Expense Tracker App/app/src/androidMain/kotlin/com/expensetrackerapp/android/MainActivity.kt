package com.expensetrackerapp // Actual package name

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.expensetrackerapp.App // Common App composable
import com.expensetrackerapp.data.DatabaseHolder
import com.expensetrackerapp.data.setApplicationContextForDb // Function to set context for DB builder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize application context for the database builder
        setApplicationContextForDb(this)

        // Initialize database holder - ideally this should be done before UI is displayed
        // if UI depends on it immediately. Launching it here.
        // For a more robust app, consider a splash screen that waits for this.
        // Our current splash screen is time-based, not dependency-based.
        CoroutineScope(Dispatchers.Main).launch { // Using Main dispatcher for launching UI-related setup
            DatabaseHolder.initialize()
            // Set content after DB is initialized or initialization is at least underway
            setContent {
                App()
            }
        }
    }
}
