package com.gavin.homeworkweek5.Soal2.model

data class AnsweredQuestion(
    val number: Int,              // nomor soal saat quiz (Q1, Q2, ...)
    val question: Question,
    val selectedAnswer: String
) {
    val isCorrect: Boolean get() = selectedAnswer == question.correctAnswer
}
