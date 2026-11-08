package lat.pam.kopidannyemil.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import lat.pam.kopidannyemil.ui.screens.ConfirmationScreen
import lat.pam.kopidannyemil.ui.screens.LoginScreen
import lat.pam.kopidannyemil.ui.screens.MainScreen
import lat.pam.kopidannyemil.ui.screens.RegisterScreen
import lat.pam.kopidannyemil.ui.screens.SignUpScreen
import lat.pam.kopidannyemil.ui.screens.SplashScreen

/**
 * Alur: Splash -> Sign Up -> (Login | Register) -> Main (Home/Order/Profile)
 *       -> Konfirmasi -> Main.
 */
@Composable
fun AppNav() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = "splash") {
        composable("splash") {
            SplashScreen(onStart = { nav.navigate("signup") })
        }
        composable("signup") {
            SignUpScreen(
                onBack = { nav.popBackStack() },
                onRegister = { nav.navigate("register") },
                onLogin = { nav.navigate("login") },
            )
        }
        composable("login") {
            LoginScreen(
                onBack = { nav.popBackStack() },
                onSuccess = {
                    nav.navigate("main") { popUpTo(nav.graph.id) { inclusive = true } }
                },
                onGoRegister = {
                    nav.navigate("register") { popUpTo("login") { inclusive = true } }
                },
            )
        }
        composable("register") {
            RegisterScreen(
                onBack = { nav.popBackStack() },
                onSuccess = {
                    nav.navigate("main") { popUpTo(nav.graph.id) { inclusive = true } }
                },
                onGoLogin = {
                    nav.navigate("login") { popUpTo("register") { inclusive = true } }
                },
            )
        }
        composable("main") {
            MainScreen(
                onOrdered = { nav.navigate("confirmation") },
                onLogout = {
                    nav.navigate("signup") { popUpTo(nav.graph.id) { inclusive = true } }
                },
            )
        }
        composable("confirmation") {
            ConfirmationScreen(
                onOk = {
                    nav.navigate("main") { popUpTo(nav.graph.id) { inclusive = true } }
                },
            )
        }
    }
}