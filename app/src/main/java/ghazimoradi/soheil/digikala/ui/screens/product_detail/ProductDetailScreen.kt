package ghazimoradi.soheil.digikala.ui.screens.product_detail

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    productId: String,
) {

    Scaffold(
        bottomBar = {

        },

        topBar = {

        }
    ) { padding ->
        Log.i("padding", padding.toString())
        LazyColumn(modifier = Modifier.padding(bottom = 70.dp)) {

        }
    }
}