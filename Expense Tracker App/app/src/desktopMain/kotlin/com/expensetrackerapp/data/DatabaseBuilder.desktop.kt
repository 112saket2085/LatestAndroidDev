package com.expensetrackerapp.data

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual fun getAppDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = File(System.getProperty("user.home"), ".expensetrackerapp/expense_tracker_desktop.db")
    dbFile.parentFile?.mkdirs() // Ensure directory exists
    return Room.databaseBuilder<AppDatabase>( // KMP extension function
        name = dbFile.absolutePath
    )
}
