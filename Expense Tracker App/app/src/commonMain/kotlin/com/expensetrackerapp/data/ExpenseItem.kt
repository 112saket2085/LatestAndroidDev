package com.expensetrackerapp.data

data class ExpenseItem(
    val id: Long, // For unique identification, useful for deletion and keys in LazyColumn
    val title: String,
    val amount: Double,
    val category: String
)
