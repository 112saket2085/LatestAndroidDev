package com.expensetrackerapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.expensetrackerapp.data.DatabaseHolder // For initialization
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DesktopAppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        // Initialize database for desktop tests if necessary
        // This might need a test-specific DB or setup.
        // For now, let's assume the same initialization path.
        runBlocking {
            // This will use the DatabaseBuilder.desktop.kt logic
            // which stores the DB in System.getProperty("user.home")
            // For tests, an in-memory or temporary file DB would be better.
            // But for this setup, we'll use the existing path.
            DatabaseHolder.initialize()
        }
    }

    @Test
    fun appDisplaysSplashScreenText() {
        // Set the content for the test
        composeTestRule.setContent {
            App() // Call the main App composable
        }

        // Look for the splash screen text.
        // This test is very basic and asserts the initial text is present.
        // Due to the delay and navigation in SplashScreen, this might be flaky
        // or pass too quickly to Dashboard. A more robust test would use Idling Resources.
        composeTestRule.onNodeWithText("Expense Tracker App", substring = true).assertExists()

        // A more robust way to test splash->dashboard navigation would be to:
        // 1. Use composeTestRule.mainClock.advanceTimeBy(2000L) (or more)
        // 2. Then assert that some element from DashboardScreen is visible.
        // However, for this initial setup, just checking for splash text is a start.
    }
}
