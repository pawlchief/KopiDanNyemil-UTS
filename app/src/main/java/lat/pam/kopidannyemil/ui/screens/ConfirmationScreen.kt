package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.data.AppState
import lat.pam.kopidannyemil.ui.components.AppButton
import lat.pam.kopidannyemil.ui.components.GlassCard
import lat.pam.kopidannyemil.ui.components.Wallpaper
import lat.pam.kopidannyemil.ui.components.rememberDrawableId
import lat.pam.kopidannyemil.ui.theme.Espresso
import lat.pam.kopidannyemil.ui.theme.Green
import lat.pam.kopidannyemil.ui.theme.GreenLight

@Composable
fun ConfirmationScreen(onOk: () -> Unit) {
    val catId = rememberDrawableId("confirmation_screen")

    val catAreaHeight = 280.dp
    val catZoom = 1.3f

    Wallpaper(image = "doodle_1") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.weight(1f))
            GlassCard(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Halo ${AppState.name},",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Espresso,
                    modifier = Modifier.align(Alignment.Start),
                )
                Spacer(Modifier.height(4.dp))
                if (catId != 0) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(catAreaHeight)
                            .clip(RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = catId),
                            contentDescription = "Kucing kurir",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer(scaleX = catZoom, scaleY = catZoom),
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier.size(110.dp).clip(CircleShape).background(GreenLight),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = null,
                            tint = Green,
                            modifier = Modifier.size(60.dp),
                        )
                    }
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    "Terima kasih sudah memesan, tunggu aja sebentar kurir kami segera mengantarkan pesananmu!",
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Espresso,
                )
            }
            Spacer(Modifier.weight(1.2f))
            Spacer(Modifier.height(16.dp))
            AppButton(label = "Oke", onClick = onOk)
        }
    }
}