package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.data.AppState
import lat.pam.kopidannyemil.data.dummyMenu
import lat.pam.kopidannyemil.data.rupiah
import lat.pam.kopidannyemil.ui.components.AppButton
import lat.pam.kopidannyemil.ui.components.AppTextField
import lat.pam.kopidannyemil.ui.components.ScreenHeader
import lat.pam.kopidannyemil.ui.components.rememberDrawableId
import lat.pam.kopidannyemil.ui.components.toast
import lat.pam.kopidannyemil.ui.theme.Brown
import lat.pam.kopidannyemil.ui.theme.Espresso

@Composable
fun AddressScreen(onOrdered: () -> Unit) {
    val context = LocalContext.current
    var fullName by rememberSaveable {
        mutableStateOf(if (AppState.name == "Nama") "" else AppState.name)
    }
    var address by rememberSaveable { mutableStateOf("") }
    var note by rememberSaveable { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize()) {
        ScreenHeader(title = "Alamat", subtitle = "Lengkapi alamat pengiriman pesananmu")

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
        ) {
            CatBanner()
            Spacer(Modifier.height(16.dp))
            OrderSummary()
            Spacer(Modifier.height(20.dp))

            AppTextField(
                value = fullName,
                onValueChange = { fullName = it },
                placeholder = "Nama Lengkap",
                leadingIcon = Icons.Filled.Person,
                error = if (submitted && fullName.isBlank()) "Nama wajib diisi" else null,
            )
            Spacer(Modifier.height(10.dp))
            AppTextField(
                value = address,
                onValueChange = { address = it },
                placeholder = "Alamat",
                singleLine = false,
                error = if (submitted && address.isBlank()) "Alamat wajib diisi" else null,
            )
            Spacer(Modifier.height(10.dp))
            AppTextField(
                value = note,
                onValueChange = { note = it },
                placeholder = "Patokan atau detail lengkap",
                singleLine = false,
            )
            Spacer(Modifier.height(24.dp))

            AppButton(
                label = "Order dan Kirim",
                onClick = {
                    if (AppState.cart.isEmpty()) {
                        toast(context, "Keranjang kosong, pilih menu di Home dulu")
                        return@AppButton
                    }
                    submitted = true
                    if (fullName.isNotBlank() && address.isNotBlank()) {
                        AppState.clearCart()
                        address = ""
                        note = ""
                        submitted = false
                        onOrdered()
                    }
                },
            )
        }
    }
}

@Composable
private fun CatBanner() {
    val catId = rememberDrawableId("kucing_kertas")
    val shape = RoundedCornerShape(22.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape)
            .clip(shape)
            .background(Color.White)
            .padding(start = 18.dp, top = 6.dp, end = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                "Hampir sampai!",
                fontSize = 19.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Espresso,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Cek lagi alamatnya ya, biar kurir cuking kami nggak nyasar.",
                fontSize = 13.sp,
                color = Brown,
            )
        }
        if (catId != 0) {
            Image(
                painter = painterResource(id = catId),
                contentDescription = "Kucing membaca kertas",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(120.dp),
            )
        }
    }
}

@Composable
private fun OrderSummary() {
    val shape = RoundedCornerShape(20.dp)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape)
            .clip(shape)
            .background(Color.White)
            .padding(16.dp),
    ) {
        Text("Ringkasan pesanan", fontWeight = FontWeight.Bold, color = Espresso)
        Spacer(Modifier.height(8.dp))
        if (AppState.cart.isEmpty()) {
            Text("Belum ada pesanan. Pilih menu di halaman Home.", color = Color.Gray)
        } else {
            AppState.cart.entries.toList().forEach { (id, qty) ->
                val item = dummyMenu.first { it.id == id }
                Row(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text("${qty}x ${item.name}", modifier = Modifier.weight(1f), color = Espresso)
                    Text(rupiah(item.price * qty), color = Espresso)
                }
            }
            HorizontalDivider(Modifier.padding(vertical = 10.dp))
            Row(Modifier.fillMaxWidth()) {
                Text("Total", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), color = Espresso)
                Text(rupiah(AppState.totalPrice), fontWeight = FontWeight.ExtraBold, color = Brown)
            }
        }
    }
}