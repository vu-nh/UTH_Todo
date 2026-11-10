package com.example.uth_todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.uth_todo.ui.theme.UTH_TodoTheme
import com.example.uth_todo.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UTH_TodoTheme {
                AppNavigation()
            }
        }
    }
}

