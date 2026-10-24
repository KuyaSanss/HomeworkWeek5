package com.gavin.homeworkweek5.Soal2.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gavin.homeworkweek5.Soal1.ui.theme.HomeworkWeek5Theme
import com.gavin.homeworkweek5.Soal2.model.AnsweredQuestion
import com.gavin.homeworkweek5.Soal2.model.Question

@Composable
fun WrongAnswerItem(answeredQuestion: AnsweredQuestion) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                text = "Q${answeredQuestion.number}: ${answeredQuestion.question.question}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Your Answer: ${answeredQuestion.selectedAnswer}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
            Text(
                text = "Correct Answer: ${answeredQuestion.question.correctAnswer}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
fun WrongAnswerItemPreview() {
    HomeworkWeek5Theme {
        WrongAnswerItem(
            answeredQuestion = AnsweredQuestion(
                number = 3,
                question = Question(
                    id = 3,
                    question = "Natal dirayakan pada bulan?",
                    options = listOf("Agustus", "Oktober", "November", "Desember"),
                    correctAnswer = "Desember"
                ),
                selectedAnswer = "Agustus"
            )
        )
    }
}
