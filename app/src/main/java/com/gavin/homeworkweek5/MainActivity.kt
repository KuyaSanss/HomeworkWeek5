package com.gavin.homeworkweek5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.gavin.homeworkweek5.Soal1.navigation.AppNavHost
import com.gavin.homeworkweek5.Soal2.navigation.QuizNavHost
import com.gavin.homeworkweek5.Soal1.ui.theme.HomeworkWeek5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeworkWeek5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Soal 1: aktifkan AppNavHost, Soal 2: aktifkan QuizNavHost
                    // AppNavHost(modifier = Modifier.padding(innerPadding))
                    QuizNavHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}