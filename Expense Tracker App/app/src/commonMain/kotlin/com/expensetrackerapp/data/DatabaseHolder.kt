package com.expensetrackerapp.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object DatabaseHolder {
    private var _db: AppDatabase? = null
    private val mutex = Mutex()

    // Potentially throw a custom, more informative exception
    val db: AppDatabase
        get() = _db ?: throw IllegalStateException("Database not initialized. Call DatabaseHolder.initialize() first.")

    // Convenience accessor for the DAO
    val dao: ExpenseDao
        get() = db.expenseDao()

    // A shared scope for database operations that should live as long as the application
    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    suspend fun initialize() {
        if (_db == null) {
            mutex.withLock {
                // Double-check in case of concurrent calls, though initialize should ideally be called once.
                if (_db == null) {
                    _db = getAppDatabaseBuilder().build()
                    // You could add migrations or pre-population logic here if needed in the future
                }
            }
        }
    }
}
