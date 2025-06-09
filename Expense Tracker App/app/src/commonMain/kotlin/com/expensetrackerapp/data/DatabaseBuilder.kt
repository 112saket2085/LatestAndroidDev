package com.expensetrackerapp.data

import androidx.room.RoomDatabase

expect fun getAppDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
