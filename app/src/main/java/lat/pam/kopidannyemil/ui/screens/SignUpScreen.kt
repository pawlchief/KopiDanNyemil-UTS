package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.ui.components.AppButton
import lat.pam.kopidannyemil.ui.components.BackBar
import lat.pam.kopidannyemil.ui.components.GlassCard
import lat.pam.kopidannyemil.ui.components.Wallpaper
import lat.pam.kopidannyemil.ui.components.rememberDrawableId
import lat.pam.kopidannyemil.ui.theme.Brown
import lat.pam.kopidannyemil.ui.theme.Espresso

@Composable
fun SignUpScreen(
    onBack: () -> Unit,
    onRegister: () -> Unit,
    onLogin: () -> Unit,
) {
    val catId = rememberDrawableId("swag")

    Wallpaper(image = "doodle_1") {
        Column(Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 8.dp)) {
            BackBar(onBack)
            Spacer(Modifier.weight(0.6f))
            GlassCard(horizontalAlignment = Alignment.CenterHorizontally) {
                if (catId != 0) {
                    Image(
                        painter = painterResource(id = catId),
                        contentDescription = "Kucing penyambut",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(200.dp),
                    )
                    Spacer(Modifier.height(4.dp))
                }
                Text("Sign up", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold, color = Espresso)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Gabung jadi member dan nikmati ngopi & nyemil yang lebih seru bareng Kopi dan Nyemil",
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    color = Brown,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(Modifier.weight(1f))
            AppButton(label = "Register", onClick = onRegister)
            Spacer(Modifier.height(12.dp))
            AppButton(label = "Login", onClick = onLogin, outlined = true)
            Spacer(Modifier.height(16.dp))
        }
    }
}