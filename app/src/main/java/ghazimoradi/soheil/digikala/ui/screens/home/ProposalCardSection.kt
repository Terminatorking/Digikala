package ghazimoradi.soheil.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.data.models.home.Slider
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.viewModels.HomeViewModel

@Composable
fun ProposalCardSection(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    var bannersList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }

    val bannersResult by viewModel.banners.collectAsState()

    when (bannersResult) {
        is NetworkResult.Success -> {
            bannersList = bannersResult.data ?: emptyList()
        }

        is NetworkResult.Error -> {
            Log.e("3636", "Banners Section error : ${bannersResult.message}")
        }

        is NetworkResult.Loading -> {}
    }

    FlowRow(
        maxItemsInEachRow = 2,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small)
    ) {
        for (item in bannersList) {
            ProposalCardItem(slider = item, navController = navController)
        }
    }
}