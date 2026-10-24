package com.gavin.homeworkweek5.Soal2.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gavin.homeworkweek5.Soal1.ui.theme.HomeworkWeek5Theme

@Composable
fun AnswerOptionButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.primaryContainer,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun AnswerOptionButtonPreview() {
    HomeworkWeek5Theme {
        AnswerOptionButton(text = "Jakarta", isSelected = true, onClick = {})
    }
}
