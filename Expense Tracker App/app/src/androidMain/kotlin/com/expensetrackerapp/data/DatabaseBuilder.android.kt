package com.expensetrackerapp.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

// This variable will be initialized by the MainActivity
internal lateinit var applicationContextForDb: Context

actual fun getAppDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    if (!::applicationContextForDb.isInitialized) {
        throw IllegalStateException("Application context for DB not initialized. Ensure setApplicationContextForDb() is called from MainActivity.")
    }
    val appContext = applicationContextForDb.applicationContext
    return Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "expense_tracker.db"
    )
}

fun setApplicationContextForDb(context: Context) {
    applicationContextForDb = context.applicationContext
}
