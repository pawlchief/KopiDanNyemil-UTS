package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.RemoveCircleOutline
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.data.AppState
import lat.pam.kopidannyemil.data.MenuItem
import lat.pam.kopidannyemil.data.dummyMenu
import lat.pam.kopidannyemil.data.rupiah
import lat.pam.kopidannyemil.ui.components.MenuImage
import lat.pam.kopidannyemil.ui.components.ScreenHeader
import lat.pam.kopidannyemil.ui.components.rememberDrawableId
import lat.pam.kopidannyemil.ui.theme.Brown
import lat.pam.kopidannyemil.ui.theme.Caramel
import lat.pam.kopidannyemil.ui.theme.Cream
import lat.pam.kopidannyemil.ui.theme.Espresso

private val filters = listOf("Semua", "Minuman", "Makanan")

@Composable
fun HomeScreen(onGoToOrder: () -> Unit) {
    var filter by rememberSaveable { mutableStateOf("Semua") }
    val visible = if (filter == "Semua") dummyMenu else dummyMenu.filter { it.category == filter }

    Column(Modifier.fillMaxSize()) {
        ScreenHeader(
            title = "Halo ${AppState.name},",
            subtitle = "Mau ngopi atau nyemil apa hari ini?",
        )

        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            filters.forEach { f ->
                FilterChip(
                    selected = filter == f,
                    onClick = { filter = f },
                    label = { Text(f) },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.White,
                        labelColor = Espresso,
                        selectedContainerColor = Caramel,
                        selectedLabelColor = Espresso,
                    ),
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item { CatBanner() }
            items(visible, key = { it.id }) { item -> MenuCard(item) }
        }

        if (AppState.cart.isNotEmpty()) {
            CartBar(onClick = onGoToOrder)
        }
    }
}

@Composable
private fun CatBanner() {
    val catId = rememberDrawableId("kucing_kacamata")
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
                "Pilih menu favoritmu!",
                fontSize = 19.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Espresso,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Oyen akan mencatat pesananmu.",
                fontSize = 13.sp,
                color = Brown,
            )
        }
        if (catId != 0) {
            Image(
                painter = painterResource(id = catId),
                contentDescription = "Kucing berkacamata",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(120.dp),
            )
        }
    }
}

@Composable
private fun MenuCard(item: MenuItem) {
    val qty = AppState.cart[item.id] ?: 0
    val shape = RoundedCornerShape(20.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape)
            .clip(shape)
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MenuImage(item, Modifier.size(76.dp))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                item.category.uppercase(),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Caramel,
            )
            Text(item.name, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Espresso)
            Text(item.description, fontSize = 12.sp, color = Color.Gray)
            Spacer(Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Cream)
                    .padding(horizontal = 10.dp, vertical = 3.dp),
            ) {
                Text(rupiah(item.price), fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Brown)
            }
        }
        if (qty == 0) {
            FilledIconButton(
                onClick = { AppState.addToCart(item.id) },
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Espresso),
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Tambah", tint = Cream)
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { AppState.removeFromCart(item.id) }) {
                    Icon(Icons.Outlined.RemoveCircleOutline, contentDescription = "Kurangi", tint = Brown)
                }
                Text("$qty", fontWeight = FontWeight.Bold, color = Espresso)
                IconButton(onClick = { AppState.addToCart(item.id) }) {
                    Icon(Icons.Filled.AddCircle, contentDescription = "Tambah", tint = Brown)
                }
            }
        }
    }
}

@Composable
private fun CartBar(onClick: () -> Unit) {
    val shape = RoundedCornerShape(18.dp)
    Row(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
            .fillMaxWidth()
            .clip(shape)
            .background(Espresso)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(Icons.Filled.ShoppingBag, contentDescription = null, tint = Cream)
        Spacer(Modifier.width(10.dp))
        Text("${AppState.totalItems} item", color = Cream, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.weight(1f))
        Text(rupiah(AppState.totalPrice), color = Caramel, fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(8.dp))
        Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = Cream)
    }
}