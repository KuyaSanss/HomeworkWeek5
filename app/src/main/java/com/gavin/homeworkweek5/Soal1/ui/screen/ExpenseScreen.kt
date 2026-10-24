package com.gavin.homeworkweek5.Soal1.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gavin.homeworkweek5.Soal1.model.Expense
import com.gavin.homeworkweek5.Soal1.ui.theme.HomeworkWeek5Theme
import com.gavin.homeworkweek5.Soal1.util.formatRupiah
import com.gavin.homeworkweek5.Soal1.viewModel.ExpenseViewModel

@Composable
fun ExpenseScreen(
    modifier: Modifier = Modifier,
    viewModel: ExpenseViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Expenses",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Total Expense : ${formatRupiah(viewModel.totalExpense)}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.nameInput,
            onValueChange = viewModel::onNameChange,
            label = { Text("Expense Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.Top) {
            OutlinedTextField(
                value = viewModel.amountInput,
                onValueChange = viewModel::onAmountChange,
                label = { Text("Amount of Expense") },
                isError = viewModel.amountError != null,
                supportingText = { viewModel.amountError?.let { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilledTonalIconButton(
                onClick = viewModel::addExpense,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add expense")
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(viewModel.expenses, key = { it.id }) { expense ->
                ExpenseItem(
                    expense = expense,
                    isEditing = viewModel.editingExpense?.id == expense.id,
                    editError = viewModel.editAmountError,
                    onEdit = { viewModel.startEdit(expense) },
                    onDelete = { viewModel.deleteExpense(expense) },
                    onEditAmountChange = viewModel::clearEditError,
                    onSaveEdit = viewModel::saveEdit,
                    onCancelEdit = viewModel::cancelEdit
                )
            }
        }
    }
}

@Composable
fun ExpenseItem(
    expense: Expense,
    isEditing: Boolean,
    editError: String?,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onEditAmountChange: () -> Unit,
    onSaveEdit: (String) -> Unit,
    onCancelEdit: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        if (isEditing) {
            var editAmountInput by remember(expense.id) { mutableStateOf(expense.amount.toString()) }

            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                Text(text = "Name : ${expense.name}", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = editAmountInput,
                    onValueChange = { value ->
                        editAmountInput = value.filter { it.isDigit() }
                        onEditAmountChange()
                    },
                    label = { Text("Amount of Expense") },
                    isError = editError != null,
                    supportingText = { editError?.let { Text(it) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onCancelEdit) { Text("Cancel") }
                    TextButton(onClick = { onSaveEdit(editAmountInput) }) { Text("Save") }
                }
            }
        } else {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Name : ${expense.name}", fontWeight = FontWeight.Bold)
                    Text(
                        text = formatRupiah(expense.amount),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit amount")
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete expense",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExpenseScreenPreview() {
    HomeworkWeek5Theme {
        ExpenseScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseItemPreview() {
    HomeworkWeek5Theme {
        ExpenseItem(
            expense = Expense(id = 1, name = "Makan Nasi", amount = 90000),
            isEditing = false,
            editError = null,
            onEdit = {},
            onDelete = {},
            onEditAmountChange = {},
            onSaveEdit = {},
            onCancelEdit = {}
        )
    }
}