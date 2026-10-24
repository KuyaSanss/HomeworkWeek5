package com.gavin.homeworkweek5.Soal2.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gavin.homeworkweek5.Soal1.ui.theme.HomeworkWeek5Theme
import com.gavin.homeworkweek5.Soal2.ViewModel.QuizViewModel
import com.gavin.homeworkweek5.Soal2.ui.widget.AnswerOptionButton

@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    onQuizFinished: () -> Unit
) {
    val question = viewModel.currentQuestion ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Question ${viewModel.currentIndex + 1}",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(text = question.question, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        question.options.forEach { option ->
            AnswerOptionButton(
                text = option,
                isSelected = viewModel.selectedAnswer == option,
                onClick = { viewModel.selectAnswer(option) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { if (viewModel.nextQuestion()) onQuizFinished() },
            enabled = viewModel.selectedAnswer != null && !viewModel.isQuizFinished,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizScreenPreview() {
    val viewModel = remember { QuizViewModel().apply { startQuiz() } }

    HomeworkWeek5Theme {
        if (viewModel.isQuizFinished) {
            QuizResultScreen(viewModel = viewModel, onRestartQuiz = viewModel::restartQuiz)
        } else {
            QuizScreen(viewModel = viewModel, onQuizFinished = {})
        }
    }
}
