package com.gavin.homeworkweek5.Soal2.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gavin.homeworkweek5.Soal2.data.QuestionData
import com.gavin.homeworkweek5.Soal2.model.AnsweredQuestion
import com.gavin.homeworkweek5.Soal2.model.Question

class QuizViewModel : ViewModel() {

    private val allQuestions = QuestionData.questions
    val totalAvailableQuestions: Int get() = allQuestions.size

    // Bonus: halaman start untuk memilih jumlah soal
    var questionCountInput by mutableStateOf("3")
        private set
    var questionCountError by mutableStateOf<String?>(null)
        private set

    private var questionCount = 3

    // State quiz
    var quizQuestions by mutableStateOf<List<Question>>(emptyList())
        private set
    var currentIndex by mutableIntStateOf(0)
        private set
    var selectedAnswer by mutableStateOf<String?>(null)
        private set
    var isQuizFinished by mutableStateOf(false)
        private set

    private val _answers = mutableStateListOf<AnsweredQuestion>()
    val answers: List<AnsweredQuestion> get() = _answers

    val currentQuestion: Question? get() = quizQuestions.getOrNull(currentIndex)
    val isLastQuestion: Boolean get() = currentIndex == quizQuestions.lastIndex
    val score: Int get() = _answers.count { it.isCorrect }
    val wrongAnswers: List<AnsweredQuestion> get() = _answers.filter { !it.isCorrect }

    fun onQuestionCountChange(value: String) {
        questionCountInput = value.filter { it.isDigit() }
        questionCountError = null
    }

    // Mengembalikan true kalau jumlah soal valid dan quiz siap dimulai
    fun startQuiz(): Boolean {
        val count = questionCountInput.toIntOrNull() ?: 0

        // Bonus: error handling jumlah soal
        if (count < 1) {
            questionCountError = "Number of questions must be at least 1"
            return false
        }
        if (count > totalAvailableQuestions) {
            questionCountError = "Number of questions cannot be more than $totalAvailableQuestions"
            return false
        }

        questionCount = count
        generateQuiz()
        return true
    }

    fun selectAnswer(answer: String) {
        selectedAnswer = answer
    }

    // Mengembalikan true kalau soal terakhir sudah dijawab (pindah ke Result)
    fun nextQuestion(): Boolean {
        // Cegah jawaban tercatat dua kali kalau Next di soal terakhir ditekan berulang
        if (isQuizFinished) return false

        val question = currentQuestion ?: return false
        val answer = selectedAnswer ?: return false

        _answers.add(AnsweredQuestion(number = currentIndex + 1, question = question, selectedAnswer = answer))

        if (isLastQuestion) {
            isQuizFinished = true
            return true
        }

        currentIndex++
        selectedAnswer = null
        return false
    }

    // Restart quiz dengan soal yang di random ulang
    fun restartQuiz() {
        generateQuiz()
    }

    private fun generateQuiz() {
        quizQuestions = allQuestions.shuffled().take(questionCount)
        currentIndex = 0
        selectedAnswer = null
        isQuizFinished = false
        _answers.clear()
    }
}
