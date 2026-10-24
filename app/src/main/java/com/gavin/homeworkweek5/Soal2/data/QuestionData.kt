package com.gavin.homeworkweek5.Soal2.data

import com.gavin.homeworkweek5.Soal2.model.Question

object QuestionData {
    val questions = listOf(
        Question(
            id = 1,
            question = "Ibu Kota Di Indonesia?",
            options = listOf("Surabaya", "Bandung", "Jakarta", "Medan"),
            correctAnswer = "Jakarta"
        ),
        Question(
            id = 2,
            question = "Apakah Mata Kuliah VP susah?",
            options = listOf("Sulit", "Biasa Saja", "Mudah", "Sangat Mudah"),
            correctAnswer = "Sangat Mudah"
        ),
        Question(
            id = 3,
            question = "Natal dirayakan pada bulan?",
            options = listOf("Agustus", "Oktober", "November", "Desember"),
            correctAnswer = "Desember"
        ),
        Question(
            id = 4,
            question = "Planet terbesar di tata surya?",
            options = listOf("Bumi", "Mars", "Jupiter", "Saturnus"),
            correctAnswer = "Jupiter"
        ),
        Question(
            id = 5,
            question = "Bahasa pemrograman utama untuk Android modern?",
            options = listOf("Kotlin", "Python", "PHP", "Swift"),
            correctAnswer = "Kotlin"
        )
    )
}
