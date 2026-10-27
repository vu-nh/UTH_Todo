package com.example.uthtodov2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.uthtodov2.navigation.AppNavigation
import com.example.uthtodov2.ui.theme.UTHTodoV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            UTHTodoV2Theme {
                AppNavigation()
            }
        }
    }
}
