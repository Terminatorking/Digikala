package ghazimoradi.soheil.digikala.ui.screens.products.productPriceChart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.productDetail.Price
import ghazimoradi.soheil.digikala.ui.components.extentions.getListTypeFromGson
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h3
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun ProductPriceChartScreen(
    navController: NavHostController,
    jsonString: String
) {

    var priceList by remember {
        mutableStateOf<List<Price>>(emptyList())
    }

    priceList = getListTypeFromGson(jsonString)

    val chartModel = createChartEntryModel(priceList)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.icon,
                imageVector = Icons.Default.Close,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(24.dp)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.price_chart),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.darkText,
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(MaterialTheme.colorScheme.searchBarBg)
        )

        Column(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.small)
                .verticalScroll(rememberScrollState())
        ) {
            PriceLineChart(chartModel, priceList)
        }
    }
}

fun createChartEntryModel(priceList: List<Price>): ChartEntryModel {
    val entryPrices = priceList.take(6).map { it.price }
    return entryModelOf(*entryPrices.toTypedArray().reversedArray())
}

fun createPersianMonthArray(priceList: List<Price>): Array<String> {
    return priceList.take(6).mapNotNull { price ->
        val numberMonth = price.persianDate.split("/").getOrNull(1)?.toIntOrNull()
        numberMonth?.let { index ->
            mapOf(
                1 to "فروردین",
                2 to "اردیبهشت",
                3 to "خرداد",
                4 to "تیر",
                5 to "مرداد",
                6 to "شهریور",
                7 to "مهر",
                8 to "آبان",
                9 to "آذر",
                10 to "دی",
                11 to "بهمن",
                12 to "اسفند"
            )[index] ?: "Invalid Month"
        }
    }.toTypedArray().reversedArray()
}