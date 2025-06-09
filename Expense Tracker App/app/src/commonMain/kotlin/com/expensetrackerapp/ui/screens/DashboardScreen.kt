package com.expensetrackerapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.expensetrackerapp.data.Expense // Changed from ExpenseItem to Expense

@OptIn(ExperimentalMaterial3Api::class) // For Scaffold
@Composable
fun DashboardScreen(
    expenses: List<Expense>, // Changed from ExpenseItem to Expense
    onAddExpenseClicked: () -> Unit,
    onDeleteExpenseClicked: (Expense) -> Unit, // Changed from ExpenseItem to Expense
    onSettingsClicked: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddExpenseClicked) {
                Icon(Icons.Filled.Add, contentDescription = "Add Expense")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Expense Dashboard") },
                actions = {
                    IconButton(onClick = onSettingsClicked) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (expenses.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No expenses yet. Tap '+' to add one!")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(expenses, key = { it.id }) { expense ->
                    ExpenseRow(expense, onDeleteExpenseClicked)
                }
            }
        }
    }
}

@Composable
fun ExpenseRow(
    expense: Expense, // Changed from ExpenseItem to Expense
    onDeleteClicked: (Expense) -> Unit // Changed from ExpenseItem to Expense
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = expense.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "Amount: ${expense.amount}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Category: ${expense.category}", style = MaterialTheme.typography.bodySmall)
                // Optionally, display the date:
                // Text(text = "Date: ${expense.date}", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = { onDeleteClicked(expense) }) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Expense")
            }
        }
    }
}
