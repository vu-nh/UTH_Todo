
package com.example.uthtodov2.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import com.example.uthtodov2.ui.screens.OnboardingScreen
import com.example.uthtodov2.ui.screens.SplashScreen

object AppRoute {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoute.SPLASH
    ) {
        composable(AppRoute.SPLASH) {
            SplashScreenRoute(navController)
        }

        composable(AppRoute.ONBOARDING) {
            val context = LocalContext.current

            OnboardingScreen(
                onFinish = {
                    Toast.makeText(
                        context,
                        "Onboarding hoàn tất!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }
}

@Composable
private fun SplashScreenRoute(
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        delay(2000)

        navController.navigate(AppRoute.ONBOARDING) {
            popUpTo(AppRoute.SPLASH) {
                inclusive = true
            }
        }
    }

    SplashScreen()
}
