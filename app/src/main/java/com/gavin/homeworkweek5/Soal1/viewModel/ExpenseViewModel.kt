package com.gavin.homeworkweek5.Soal1.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gavin.homeworkweek5.Soal1.model.Expense

class ExpenseViewModel : ViewModel() {

    private var nextId = 1

    // Daftar expense
    private val _expenses = mutableStateListOf<Expense>()
    val expenses: List<Expense> get() = _expenses

    // Total selalu dihitung dari daftar expense, jadi otomatis update saat tambah/hapus/edit
    val totalExpense: Long get() = _expenses.sumOf { it.amount }

    // State input form
    var nameInput by mutableStateOf("")
        private set
    var amountInput by mutableStateOf("")
        private set
    var amountError by mutableStateOf<String?>(null)
        private set

    fun onNameChange(value: String) {
        nameInput = value
    }

    fun onAmountChange(value: String) {
        amountInput = value.filter { it.isDigit() }
        amountError = null
    }

    fun addExpense() {
        val amount = amountInput.toLongOrNull() ?: 0L

        // Error handling: amount tidak boleh 0
        if (amount <= 0L) {
            amountError = "Amount must be greater than 0"
            return
        }

        _expenses.add(Expense(id = nextId++, name = nameInput, amount = amount))

        nameInput = ""
        amountInput = ""
    }

    fun deleteExpense(expense: Expense) {
        _expenses.remove(expense)
    }

    // Bonus: edit amount
    var editingExpense by mutableStateOf<Expense?>(null)
        private set
    var editAmountError by mutableStateOf<String?>(null)
        private set

    fun startEdit(expense: Expense) {
        editingExpense = expense
        editAmountError = null
    }

    fun clearEditError() {
        editAmountError = null
    }

    fun saveEdit(newAmountInput: String) {
        val expense = editingExpense ?: return
        val newAmount = newAmountInput.toLongOrNull() ?: 0L

        if (newAmount <= 0L) {
            editAmountError = "Amount must be greater than 0"
            return
        }

        val index = _expenses.indexOfFirst { it.id == expense.id }
        if (index != -1) {
            _expenses[index] = expense.copy(amount = newAmount)
        }
        cancelEdit()
    }

    fun cancelEdit() {
        editingExpense = null
        editAmountError = null
    }
}
