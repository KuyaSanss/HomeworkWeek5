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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gavin.homeworkweek5.Soal1.ui.theme.HomeworkWeek5Theme
import com.gavin.homeworkweek5.Soal2.ViewModel.QuizViewModel
import com.gavin.homeworkweek5.Soal2.ui.widget.WrongAnswerItem

@Composable
fun QuizResultScreen(
    viewModel: QuizViewModel,
    onRestartQuiz: () -> Unit
) {
    val wrongAnswers = viewModel.wrongAnswers

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Quiz Finished!", style = MaterialTheme.typography.headlineSmall)
        Text(
            text = "Your Score: ${viewModel.score} / ${viewModel.answers.size}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (wrongAnswers.isEmpty()) {
            Text(
                text = "Perfect! No wrong answers.",
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            wrongAnswers.forEach { answeredQuestion ->
                WrongAnswerItem(answeredQuestion = answeredQuestion)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRestartQuiz,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Restart Quiz")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizResultScreenPreview() {
    HomeworkWeek5Theme {
        QuizResultScreen(viewModel = QuizViewModel(), onRestartQuiz = {})
    }
}
