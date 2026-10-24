package com.gavin.homeworkweek5.Soal1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gavin.homeworkweek5.Soal1.ui.screen.ExpenseScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.EXPENSE,
        modifier = modifier
    ) {
        composable(Routes.EXPENSE) {
            ExpenseScreen()
        }
    }
}