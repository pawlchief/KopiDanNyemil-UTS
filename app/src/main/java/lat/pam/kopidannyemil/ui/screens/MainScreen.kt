package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import lat.pam.kopidannyemil.data.AppState
import lat.pam.kopidannyemil.ui.components.Wallpaper
import lat.pam.kopidannyemil.ui.theme.Brown
import lat.pam.kopidannyemil.ui.theme.Espresso
import lat.pam.kopidannyemil.ui.theme.Peach

private data class TabItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

private val tabs = listOf(
    TabItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
    TabItem("Order", Icons.Filled.ShoppingBag, Icons.Outlined.ShoppingBag),
    TabItem("Profile", Icons.Filled.Person, Icons.Outlined.Person),
)

@Composable
fun MainScreen(
    onOrdered: () -> Unit,
    onLogout: () -> Unit,
) {
    var tab by rememberSaveable { mutableIntStateOf(0) }

    Wallpaper(image = "doodle_2") {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    tabs.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = tab == index,
                            onClick = { tab = index },
                            icon = {
                                val icon = if (tab == index) item.selectedIcon else item.unselectedIcon
                                if (index == 1) {
                                    BadgedBox(
                                        badge = {
                                            if (AppState.totalItems > 0) {
                                                Badge { Text("${AppState.totalItems}") }
                                            }
                                        },
                                    ) { Icon(icon, contentDescription = item.label) }
                                } else {
                                    Icon(icon, contentDescription = item.label)
                                }
                            },
                            label = { Text(item.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Espresso,
                                selectedTextColor = Espresso,
                                indicatorColor = Peach,
                                unselectedIconColor = Brown,
                                unselectedTextColor = Brown,
                            ),
                        )
                    }
                }
            },
        ) { padding ->
            Box(Modifier.padding(padding)) {
                when (tab) {
                    0 -> HomeScreen(onGoToOrder = { tab = 1 })
                    1 -> AddressScreen(onOrdered = onOrdered)
                    else -> ProfileScreen(onLogout = onLogout)
                }
            }
        }
    }
}