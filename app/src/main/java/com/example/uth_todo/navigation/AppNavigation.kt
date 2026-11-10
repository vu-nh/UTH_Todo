package com.example.uth_todo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.uth_todo.ui.screens.SplashScreen
import com.example.uth_todo.ui.screens.OnboardingScreen
import com.example.uth_todo.ui.screens.LoginScreen
import com.example.uth_todo.ui.screens.HomeScreen
import com.example.uth_todo.ui.screens.ProfileScreen

object AppRoutes {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val LOGIN = "login"
    const val HOME = "home"
    const val PROFILE = "profile"
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.SPLASH
    ) {

        // 1. Splash
        composable(AppRoutes.SPLASH) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(AppRoutes.ONBOARDING) {
                        popUpTo(AppRoutes.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // 2. Onboarding
        composable(AppRoutes.ONBOARDING) {
            OnboardingScreen(
                onNavigateToLogin = {
                    navController.navigate(AppRoutes.LOGIN) {
                        popUpTo(AppRoutes.ONBOARDING) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // 3. Login
        composable(AppRoutes.LOGIN) {
            LoginScreen(
                onNavigateToHome = {
                    // Chuyển sang Home và xóa Login khỏi back stack để không back lại được
                    navController.navigate(AppRoutes.HOME) {
                        popUpTo(AppRoutes.LOGIN) {
                            inclusive = true
                        }
                    }
                },
                onGuestClick = {
                    navController.navigate(AppRoutes.HOME) {
                        popUpTo(AppRoutes.LOGIN) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // 4. Home
        composable(AppRoutes.HOME) {
            HomeScreen(
                onNavigateToProfile = {
                    navController.navigate(AppRoutes.PROFILE)
                }
            )
        }

        // 5. Profile
        composable(AppRoutes.PROFILE) {
            ProfileScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onLinkGoogleClick = {
                    // Liên kết Google với Firebase sau
                },
                onLogoutClick = {
                    navController.navigate(AppRoutes.LOGIN) {
                        popUpTo(AppRoutes.HOME) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}