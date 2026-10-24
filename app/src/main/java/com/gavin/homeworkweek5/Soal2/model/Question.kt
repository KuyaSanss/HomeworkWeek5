package com.gavin.homeworkweek5.Soal2.model

data class Question(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)
