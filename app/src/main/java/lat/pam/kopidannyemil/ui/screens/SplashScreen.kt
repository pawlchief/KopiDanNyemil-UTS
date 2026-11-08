package lat.pam.kopidannyemil.ui.screens

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.RectF
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.EmojiFoodBeverage
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.ui.components.Wallpaper
import lat.pam.kopidannyemil.ui.components.rememberDrawableId
import lat.pam.kopidannyemil.ui.theme.Caramel
import lat.pam.kopidannyemil.ui.theme.Cream
import lat.pam.kopidannyemil.ui.theme.Espresso
import lat.pam.kopidannyemil.ui.theme.Peach

private val titleFont = FontFamily.Serif
private val taglineFont = FontFamily(Typeface.create("sans-serif-light", Typeface.NORMAL))
private val buttonFont = FontFamily(Typeface.create("sans-serif-medium", Typeface.NORMAL))

@Composable
fun SplashScreen(onStart: () -> Unit) {
    val logoId = rememberDrawableId("logo")

    Wallpaper(image = "splash_screen") {
        Box(
            Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0.25f),
                            Color.Black.copy(alpha = 0.50f),
                            Color.Black.copy(alpha = 0.78f),
                        )
                    )
                )
        )

        Floating(Icons.Filled.Coffee, Cream, 34, -15f, 0.55f, Modifier.align(Alignment.TopStart).padding(start = 24.dp, top = 36.dp))
        Floating(Icons.Filled.Cookie, Peach, 30, 20f, 0.55f, Modifier.align(Alignment.TopEnd).padding(end = 28.dp, top = 56.dp))
        Floating(Icons.Filled.EmojiFoodBeverage, Peach, 28, -10f, 0.4f, Modifier.align(Alignment.CenterStart).padding(start = 12.dp, top = 120.dp))
        Floating(Icons.Filled.Cake, Cream, 28, 12f, 0.4f, Modifier.align(Alignment.CenterEnd).padding(end = 14.dp, top = 150.dp))
        Floating(Icons.Filled.Favorite, Caramel, 14, 15f, 0.7f, Modifier.align(Alignment.CenterStart).padding(start = 46.dp, top = 210.dp))
        Floating(Icons.Filled.Favorite, Peach, 12, -12f, 0.7f, Modifier.align(Alignment.CenterEnd).padding(end = 50.dp, top = 80.dp))
        Floating(Icons.Filled.AutoAwesome, Cream, 12, 0f, 0.5f, Modifier.align(Alignment.BottomStart).padding(start = 40.dp, bottom = 150.dp))
        Floating(Icons.Filled.AutoAwesome, Peach, 14, 0f, 0.5f, Modifier.align(Alignment.BottomEnd).padding(end = 36.dp, bottom = 170.dp))

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.weight(0.8f))

            ArchText("안녕하세요!")

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    Modifier
                        .size(300.dp)
                        .background(
                            brush = Brush.radialGradient(
                                listOf(Cream.copy(alpha = 0.35f), Color.Transparent)
                            ),
                            shape = CircleShape,
                        )
                )

                if (logoId != 0) {
                    Image(
                        painter = painterResource(id = logoId),
                        contentDescription = "Logo Kopi dan Nyemil",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.width(280.dp).offset(x = 14.dp),
                    )
                } else {
                    Icon(
                        Icons.Filled.LocalCafe,
                        contentDescription = null,
                        modifier = Modifier.size(96.dp),
                        tint = Peach,
                    )
                }

                Sparkle(Modifier.offset(x = (-130).dp, y = (-70).dp), Peach, 22)
                Sparkle(Modifier.offset(x = 125.dp, y = (-100).dp), Cream, 14)
                Sparkle(Modifier.offset(x = (-110).dp, y = 20.dp), Cream, 12)
                Sparkle(Modifier.offset(x = 135.dp, y = 10.dp), Peach, 18)
                Sparkle(Modifier.offset(x = (-95).dp, y = 100.dp), Caramel, 16)
                Sparkle(Modifier.offset(x = 100.dp, y = 105.dp), Cream, 13)
            }

            Spacer(Modifier.height(8.dp))

            Text(
                "Kopi dan Nyemil",
                fontSize = 36.sp,
                fontFamily = titleFont,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.45f),
                        offset = Offset(0f, 4f),
                        blurRadius = 10f,
                    )
                ),
            )

            Spacer(Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.width(40.dp).height(1.dp).background(Cream.copy(alpha = 0.6f)))
                Icon(
                    Icons.Filled.Coffee,
                    contentDescription = null,
                    tint = Caramel,
                    modifier = Modifier.padding(start = 10.dp).size(18.dp),
                )
                Icon(
                    Icons.Filled.Cookie,
                    contentDescription = null,
                    tint = Caramel,
                    modifier = Modifier.padding(start = 6.dp).size(18.dp),
                )
                Icon(
                    Icons.Filled.BakeryDining,
                    contentDescription = null,
                    tint = Caramel,
                    modifier = Modifier.padding(horizontal = 6.dp).padding(end = 4.dp).size(18.dp),
                )
                Box(Modifier.width(40.dp).height(1.dp).background(Cream.copy(alpha = 0.6f)))
            }

            Spacer(Modifier.height(10.dp))

            Text(
                "Get a taste of your wish",
                fontSize = 16.sp,
                fontFamily = taglineFont,
                color = Cream,
                textAlign = TextAlign.Center,
                letterSpacing = 1.5.sp,
            )

            Spacer(Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Dot(Cream.copy(alpha = 0.4f), 6)
                Dot(Caramel, 9)
                Dot(Cream.copy(alpha = 0.4f), 6)
            }

            Spacer(Modifier.height(14.dp))

            Button(
                onClick = onStart,
                modifier = Modifier.fillMaxWidth().height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Caramel,
                    contentColor = Espresso,
                ),
            ) {
                Text(
                    "Start now",
                    fontFamily = buttonFont,
                    fontSize = 16.sp,
                )
                Spacer(Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }

            Spacer(Modifier.height(36.dp))
        }
    }
}

@Composable
private fun ArchText(text: String, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth().height(120.dp)) {
        Canvas(Modifier.fillMaxSize()) {
            val textSizePx = 30.sp.toPx()
            val r = size.width * 0.6f
            val half = 50f
            val top = textSizePx + 8.dp.toPx()
            val cx = size.width / 2f
            val cy = top + r

            val path = Path().apply {
                addArc(RectF(cx - r, cy - r, cx + r, cy + r), 270f - half, half * 2)
            }
            val length = PathMeasure(path, false).length

            val fill = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                textSize = textSizePx
                typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
                letterSpacing = 0.15f
                textAlign = Paint.Align.LEFT
                color = Cream.toArgb()
            }
            val outline = Paint(fill).apply {
                style = Paint.Style.STROKE
                strokeWidth = 6.dp.toPx()
                strokeJoin = Paint.Join.ROUND
                color = Espresso.toArgb()
            }

            val startOffset = (length - fill.measureText(text)) / 2f

            val canvas = drawContext.canvas.nativeCanvas
            canvas.drawTextOnPath(text, path, startOffset, 0f, outline)
            canvas.drawTextOnPath(text, path, startOffset, 0f, fill)

            val r2 = r - 8.dp.toPx()
            drawArc(
                color = Caramel,
                startAngle = 270f - half,
                sweepAngle = half * 2,
                useCenter = false,
                topLeft = Offset(cx - r2, cy - r2),
                size = Size(r2 * 2, r2 * 2),
                style = Stroke(width = 2.dp.toPx()),
            )
        }
        Icon(
            Icons.Filled.AutoAwesome, contentDescription = null, tint = Peach,
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 12.dp, bottom = 6.dp).size(18.dp),
        )
        Icon(
            Icons.Filled.AutoAwesome, contentDescription = null, tint = Peach,
            modifier = Modifier.align(Alignment.BottomEnd).padding(end = 12.dp, bottom = 6.dp).size(18.dp),
        )
        Icon(
            Icons.Filled.AutoAwesome, contentDescription = null, tint = Cream,
            modifier = Modifier.align(Alignment.TopCenter).size(14.dp),
        )
    }
}

@Composable
private fun Sparkle(modifier: Modifier, tint: Color, size: Int) {
    Icon(
        Icons.Filled.AutoAwesome,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp).rotate(12f),
    )
}

@Composable
private fun Floating(
    icon: ImageVector,
    tint: Color,
    size: Int,
    angle: Float,
    alpha: Float,
    modifier: Modifier,
) {
    Icon(
        icon,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp).rotate(angle).alpha(alpha),
    )
}

@Composable
private fun Dot(color: Color, size: Int) {
    Box(Modifier.size(size.dp).background(color, CircleShape))
}