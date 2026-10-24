package com.gavin.homeworkweek5.Soal2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gavin.homeworkweek5.Soal1.ui.theme.HomeworkWeek5Theme
import com.gavin.homeworkweek5.Soal2.ViewModel.QuizViewModel
import com.gavin.homeworkweek5.Soal2.ui.screen.QuizResultScreen
import com.gavin.homeworkweek5.Soal2.ui.screen.QuizScreen
import com.gavin.homeworkweek5.Soal2.ui.screen.QuizStartScreen

@Composable
fun QuizNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    quizViewModel: QuizViewModel = viewModel()   // satu ViewModel dipakai bersama oleh semua screen quiz
) {
    NavHost(
        navController = navController,
        startDestination = QuizRoutes.START,
        modifier = modifier
    ) {
        composable(QuizRoutes.START) {
            QuizStartScreen(
                viewModel = quizViewModel,
                onStartQuiz = { navController.navigate(QuizRoutes.QUIZ) }
            )
        }
        composable(QuizRoutes.QUIZ) {
            QuizScreen(
                viewModel = quizViewModel,
                onQuizFinished = {
                    navController.navigate(QuizRoutes.RESULT) {
                        popUpTo(QuizRoutes.QUIZ) { inclusive = true }
                    }
                }
            )
        }
        composable(QuizRoutes.RESULT) {
            QuizResultScreen(
                viewModel = quizViewModel,
                onRestartQuiz = {
                    quizViewModel.restartQuiz()
                    navController.navigate(QuizRoutes.QUIZ) {
                        popUpTo(QuizRoutes.RESULT) { inclusive = true }
                    }
                }
            )
        }
    }
}

// Preview alur lengkap (Start -> Quiz -> Result). Gunakan Interactive Mode untuk mencobanya.
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizNavHostPreview() {
    HomeworkWeek5Theme {
        QuizNavHost(quizViewModel = QuizViewModel())
    }
}
