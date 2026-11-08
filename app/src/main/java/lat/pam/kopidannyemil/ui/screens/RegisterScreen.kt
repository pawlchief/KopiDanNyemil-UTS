package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.data.AppState
import lat.pam.kopidannyemil.ui.components.AppButton
import lat.pam.kopidannyemil.ui.components.AppTextField
import lat.pam.kopidannyemil.ui.components.BackBar
import lat.pam.kopidannyemil.ui.components.GlassCard
import lat.pam.kopidannyemil.ui.components.Wallpaper
import lat.pam.kopidannyemil.ui.components.toast
import lat.pam.kopidannyemil.ui.theme.Brown
import lat.pam.kopidannyemil.ui.theme.Espresso

/** 4. Register */
@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onSuccess: () -> Unit,
    onGoLogin: () -> Unit,
) {
    val context = LocalContext.current
    var fullName by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

    Wallpaper(image = "doodle_1") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 8.dp),
        ) {
            BackBar(onBack)
            Spacer(Modifier.height(8.dp))

            GlassCard {
                Text("Register", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, color = Espresso)
                Spacer(Modifier.height(20.dp))

                AppTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = "nama lengkap",
                    leadingIcon = Icons.Filled.Badge,
                    error = if (submitted && fullName.isBlank()) "Nama lengkap wajib diisi" else null,
                )
                Spacer(Modifier.height(10.dp))
                AppTextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = "username",
                    leadingIcon = Icons.Filled.Person,
                    error = if (submitted && username.trim().length < 3) "Minimal 3 karakter" else null,
                )
                Spacer(Modifier.height(10.dp))
                AppTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "password",
                    leadingIcon = Icons.Filled.Lock,
                    isPassword = true,
                    error = if (submitted && password.length < 6) "Minimal 6 karakter" else null,
                )
                Spacer(Modifier.height(20.dp))
                AppButton(
                    label = "Register",
                    onClick = {
                        submitted = true
                        val valid = fullName.isNotBlank() &&
                                username.trim().length >= 3 &&
                                password.length >= 6
                        if (valid) {
                            if (AppState.register(fullName.trim(), username.trim(), password)) {
                                onSuccess()
                            } else {
                                toast(context, "Username sudah dipakai")
                            }
                        }
                    },
                )
                Spacer(Modifier.height(4.dp))
                TextButton(onClick = onGoLogin, modifier = Modifier.fillMaxWidth()) {
                    Text("Sudah punya akun? Login", color = Brown)
                }
            }
        }
    }
}