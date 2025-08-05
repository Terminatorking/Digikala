package ghazimoradi.soheil.digikala.ui.screens.products.productPriceChart

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.compose.component.shape.shader.verticalGradient
import com.patrykandpatrick.vico.core.axis.horizontal.createHorizontalAxis
import com.patrykandpatrick.vico.core.component.shape.DashedShape
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.scroll.InitialScroll
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.productDetail.Price
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.veryExtraSmall

@Composable
fun PriceLineChart(
    model: ChartEntryModel,
    priceList: List<Price>,
    scrollable: Boolean = true,
    initialScroll: InitialScroll = InitialScroll.Start,
) {
    ChartCard(
        title = stringResource(id = R.string.product_price_chart),
        subTitle = stringResource(id = R.string.product_price_chart_time)
    ) {
        Chart(
            chart = lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = MaterialTheme.colorScheme.DigiKalaRed,
                        lineBackgroundShader = verticalGradient(
                            arrayOf(
                                MaterialTheme.colorScheme.DigiKalaRed.copy(0.5f),
                                MaterialTheme.colorScheme.DigiKalaRed.copy(alpha = 0f)
                            ),
                        )
                    )
                )
            ),
            model = model,
            startAxis = rememberStartAxis(),
            bottomAxis = createHorizontalAxis {
                guideline = LineComponent(
                    color = Color.Red.toArgb(),
                    thicknessDp = 1.dp.value,
                    shape = DashedShape(
                        shape = Shapes.rectShape,
                        dashLengthDp = 2.dp.value,
                        gapLengthDp = 4.dp.value,
                    ),
                )
            },
            chartScrollSpec = rememberChartScrollSpec(
                isScrollEnabled = scrollable,
                initialScroll = initialScroll
            )
        )

        Row{
            Spacer(modifier = Modifier.weight(0.142f))
            val persianMonths = createPersianMonthArray(priceList)

            persianMonths.forEach { monthName ->
                Text(
                    style = MaterialTheme.typography.veryExtraSmall,
                    modifier = Modifier
                        .weight(0.142f),
                    text = monthName,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

