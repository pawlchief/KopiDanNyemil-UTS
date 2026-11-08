package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.data.AppState
import lat.pam.kopidannyemil.ui.components.AppButton
import lat.pam.kopidannyemil.ui.components.AppTextField
import lat.pam.kopidannyemil.ui.components.ScreenHeader
import lat.pam.kopidannyemil.ui.components.rememberDrawableId
import lat.pam.kopidannyemil.ui.components.toast
import lat.pam.kopidannyemil.ui.theme.Caramel
import lat.pam.kopidannyemil.ui.theme.Espresso
import lat.pam.kopidannyemil.ui.theme.Peach

@Composable
fun ProfileScreen(onLogout: () -> Unit) {
    val context = LocalContext.current
    var name by remember { mutableStateOf(AppState.name) }
    val avatarId = rememberDrawableId("foto_profil")

    Column(Modifier.fillMaxSize()) {
        ScreenHeader(title = "Halo ${AppState.name},", subtitle = "Profile Saya")

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(Brush.verticalGradient(listOf(Peach, Caramel))),
                contentAlignment = Alignment.Center,
            ) {
                if (avatarId != 0) {
                    Image(
                        painter = painterResource(id = avatarId),
                        contentDescription = "Foto profil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                } else {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = null,
                        tint = Espresso,
                        modifier = Modifier.size(52.dp),
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            Text("Profile Setting", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Espresso)
            Spacer(Modifier.height(12.dp))

            AppTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = "nama",
                leadingIcon = Icons.Filled.Badge,
            )
            Spacer(Modifier.height(24.dp))

            AppButton(
                label = "Save",
                onClick = {
                    if (name.isBlank()) {
                        toast(context, "Nama tidak boleh kosong")
                    } else {
                        AppState.updateName(name.trim())
                        toast(context, "Profil berhasil disimpan")
                    }
                },
            )
            Spacer(Modifier.height(12.dp))
            AppButton(
                label = "Logout",
                outlined = true,
                onClick = {
                    AppState.logout()
                    onLogout()
                },
            )
        }
    }
}