package com.gavin.homeworkweek5.Soal2.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gavin.homeworkweek5.Soal1.ui.theme.HomeworkWeek5Theme
import com.gavin.homeworkweek5.Soal2.ViewModel.QuizViewModel

// Bonus: halaman start untuk memilih jumlah soal
@Composable
fun QuizStartScreen(
    viewModel: QuizViewModel,
    onStartQuiz: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Quiz", style = MaterialTheme.typography.headlineSmall)
        Text(
            text = "Choose number of questions (max ${viewModel.totalAvailableQuestions})",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.questionCountInput,
            onValueChange = viewModel::onQuestionCountChange,
            label = { Text("Number of Questions") },
            isError = viewModel.questionCountError != null,
            supportingText = { viewModel.questionCountError?.let { Text(it) } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { if (viewModel.startQuiz()) onStartQuiz() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Start Quiz")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizStartScreenPreview() {
    HomeworkWeek5Theme {
        QuizStartScreen(viewModel = QuizViewModel(), onStartQuiz = {})
    }
}
