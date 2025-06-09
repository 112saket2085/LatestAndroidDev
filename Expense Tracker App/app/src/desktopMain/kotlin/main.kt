import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.expensetrackerapp.App // Common App composable
import com.expensetrackerapp.data.DatabaseHolder
import kotlinx.coroutines.runBlocking // For synchronous initialization on desktop

fun main() = application {
    // Initialize database before starting the UI application block
    // For desktop, runBlocking is acceptable here for initial setup.
    runBlocking {
        DatabaseHolder.initialize()
    }

    Window(onCloseRequest = ::exitApplication, title = "Expense Tracker App") {
        App() // Call the common App composable
    }
}
