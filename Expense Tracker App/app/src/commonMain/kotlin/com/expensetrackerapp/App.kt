package com.expensetrackerapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.expensetrackerapp.data.DatabaseHolder
import com.expensetrackerapp.data.Expense // Changed from ExpenseItem
import com.expensetrackerapp.ui.screens.*
import com.expensetrackerapp.ui.theme.AppDarkColorScheme
import com.expensetrackerapp.ui.theme.AppLightColorScheme
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock // For Clock.System.now()

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
    var isDarkMode by remember { mutableStateOf(false) }
    val currentColorScheme = if (isDarkMode) AppDarkColorScheme else AppLightColorScheme

    val composableScope = rememberCoroutineScope()
    // remember the DAO instance to avoid re-fetching on recompositions
    val expenseDao = remember { DatabaseHolder.dao }

    val expensesState by remember(expenseDao) {
        expenseDao.getAllExpenses()
    }.stateIn( // Use stateIn to convert Flow to State
        scope = DatabaseHolder.applicationScope, // Application-wide scope
        started = SharingStarted.WhileSubscribed(5000L), // Keep flow active while subscribed + 5s
        initialValue = emptyList() // Initial value before flow emits
    ).collectAsState()

    MaterialTheme(colorScheme = currentColorScheme) {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (currentScreen) {
                Screen.Splash -> SplashScreen { currentScreen = Screen.Dashboard }
                Screen.Dashboard -> DashboardScreen(
                    expenses = expensesState, // Use state from DB
                    onAddExpenseClicked = { currentScreen = Screen.AddExpense },
                    onDeleteExpenseClicked = { expense ->
                        composableScope.launch { // Launch coroutine for DB operation
                            expenseDao.delete(expense)
                        }
                    },
                    onSettingsClicked = { currentScreen = Screen.Settings }
                )
                Screen.AddExpense -> AddExpenseScreen(
                    onAddExpense = { title, amount, category ->
                        composableScope.launch { // Launch coroutine for DB operation
                            val newExpense = Expense( // Create Expense entity
                                title = title,
                                amount = amount,
                                category = category,
                                date = Clock.System.now().toEpochMilliseconds() // Use Clock
                            )
                            expenseDao.insert(newExpense)
                            currentScreen = Screen.Dashboard
                        }
                    },
                    onNavigateBack = { currentScreen = Screen.Dashboard }
                )
                Screen.Settings -> SettingsScreen(
                    isDarkMode = isDarkMode,
                    onDarkModeToggle = { newDarkModeState -> isDarkMode = newDarkModeState },
                    onNavigateBack = { currentScreen = Screen.Dashboard }
                )
            }
        }
    }
}

sealed class Screen {
    object Splash : Screen()
    object Dashboard : Screen()
    object AddExpense : Screen()
    object Settings : Screen()
}
