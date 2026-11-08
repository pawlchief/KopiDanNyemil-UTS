package lat.pam.kopidannyemil.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Dark = Color(0xFF1E1E1E)
val Cream = Color(0xFFFFF4E8)
val Peach = Color(0xFFF4C9A0)
val Brown = Color(0xFF8B5E3C)
val GreenLight = Color(0xFFD4F1D8)
val Green = Color(0xFF2E9E4F)
val Espresso = Color(0xFF2B1B12)
val Mocha = Color(0xFF4A2E20)
val Latte = Color(0xFFD9B99B)
val Caramel = Color(0xFFC68642)
val Paper = Color(0xFFFFFAF4)

@Composable
fun KopiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Espresso,
            onPrimary = Color.White,
            secondary = Caramel,
            background = Paper,
            surface = Color.White,
            onSurface = Espresso,
        ),
        content = content,
    )
}