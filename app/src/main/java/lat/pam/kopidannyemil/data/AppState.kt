
package lat.pam.kopidannyemil.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.DonutLarge
import androidx.compose.material.icons.filled.EmojiFoodBeverage
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val category: String,
    val icon: ImageVector,
    val image: String,
)

val dummyMenu: List<MenuItem> = listOf(
    MenuItem(
        "m1", "Es Kopi Susu", "Kopi susu dingin dengan foam lembut",
        24000, "Minuman", Icons.Filled.LocalCafe, "kopi_susu",
    ),
    MenuItem(
        "m2", "Cappuccino Latte Art", "Espresso, susu, dengan latte art lucu",
        27000, "Minuman", Icons.Filled.Coffee, "latte_art",
    ),
    MenuItem(
        "m3", "Americano", "Espresso dengan air panas, rasa kopi yang bold",
        25000, "Minuman", Icons.Filled.EmojiFoodBeverage, "americano",
    ),
    MenuItem(
        "m4", "Bungeoppang", "Roti ikan khas Korea, hangat dan renyah",
        15000, "Makanan", Icons.Filled.BakeryDining, "bungeoppang",
    ),
    MenuItem(
        "m5", "Salt Bread", "Roti gurih dengan mentega dan taburan garam",
        12000, "Makanan", Icons.Filled.Fastfood, "salt_bread",
    ),
    MenuItem(
        "m6", "Cromboloni", "Pastry croissant renyah dengan isian krim lembut",
        14000, "Makanan", Icons.Filled.DonutLarge, "cromboloni",
    ),
)

fun rupiah(v: Int): String =
    "Rp " + v.toString().reversed().chunked(3).joinToString(".").reversed()

object AppState {

    private val passwords = mutableMapOf<String, String>()

    private val fullNames = mutableMapOf<String, String>()

    private var currentUser: String? = null

    var name by mutableStateOf("Nama")
        private set

    val cart = mutableStateMapOf<String, Int>()

    fun register(
        fullName: String,
        username: String,
        password: String
    ): Boolean {
        if (passwords.containsKey(username)) return false

        passwords[username] = password
        fullNames[username] = fullName
        currentUser = username
        name = fullName
        cart.clear()

        return true
    }

    fun login(username: String, password: String): Boolean {
        if (passwords[username] != password) return false

        currentUser = username
        name = fullNames[username] ?: username
        cart.clear()

        return true
    }

    fun updateName(newName: String) {
        name = newName
        currentUser?.let { username ->
            fullNames[username] = newName
        }
    }

    fun logout() {
        currentUser = null
        name = "Nama"
        cart.clear()
    }

    fun addToCart(id: String) {
        cart[id] = (cart[id] ?: 0) + 1
    }

    fun removeFromCart(id: String) {
        val q = (cart[id] ?: 0) - 1
        if (q <= 0) cart.remove(id) else cart[id] = q
    }

    fun clearCart() = cart.clear()

    val totalItems: Int
        get() = cart.values.sum()

    val totalPrice: Int
        get() = cart.entries.sumOf { (id, qty) ->
            dummyMenu.first { it.id == id }.price * qty
        }
}
