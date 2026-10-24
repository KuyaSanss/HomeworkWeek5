package com.gavin.homeworkweek5.Soal2.model

data class AnsweredQuestion(
    val number: Int,
    val question: Question,
    val selectedAnswer: String
) {
    val isCorrect: Boolean get() = selectedAnswer == question.correctAnswer
}
