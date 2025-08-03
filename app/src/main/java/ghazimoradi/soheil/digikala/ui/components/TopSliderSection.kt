package ghazimoradi.soheil.digikala.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ghazimoradi.soheil.digikala.data.model.home.Slider
import ghazimoradi.soheil.digikala.data.model.productDetail.SliderImage
import ghazimoradi.soheil.digikala.ui.theme.*
import kotlinx.coroutines.delay

@Composable
@Suppress("Deprecation")
fun TopSliderSection(
    homeSliders: List<Slider> = emptyList(),
    productDetailSliders: List<SliderImage> = emptyList(),
) {
    var isFromProductDetail = false

    if (homeSliders.isEmpty()) {
        isFromProductDetail = true
    }

    if (productDetailSliders.isEmpty()) {
        isFromProductDetail = false
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isFromProductDetail) 300.dp else 200.dp)
    ) {
        Column(
            modifier = if (!isFromProductDetail) Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    horizontal = LocalSpacing.current.extraSmall,
                    vertical = LocalSpacing.current.small
                ) else Modifier.fillMaxSize()
        ) {
            val pagerState = rememberPagerState()
            var imageUrl by remember {
                mutableStateOf("")
            }

            Box {
                HorizontalPager(
                    count = if (isFromProductDetail) productDetailSliders.size else homeSliders.size,
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = LocalSpacing.current.medium),
                    modifier = Modifier.fillMaxWidth()
                ) { index ->
                    imageUrl =
                        if (isFromProductDetail) productDetailSliders[index].image else homeSliders[index].image
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        val painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = imageUrl)
                                .apply(
                                    block = fun ImageRequest.Builder.() {
                                        scale(Scale.FILL)
                                    }
                                )
                                .build()
                        )
                        val gradientBrush = Brush.verticalGradient(
                            colors = listOf(
                                Transparent,
                                Black.copy(0.6f)
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY // Or a specific endY based on image height
                        )
                        Image(
                            painter = painter,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(LocalSpacing.current.small)
                                .clip(LocalShape.current.medium)
                                .drawWithContent {
                                    drawContent()
                                    drawRect(brush = gradientBrush, blendMode = BlendMode.SrcOver)
                                }
                                .fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(LocalSpacing.current.semiLarge),
                    activeColor = MaterialTheme.colors.DigiKalaRed,
                    inactiveColor = White,
                    indicatorWidth = LocalSpacing.current.small,
                    indicatorHeight = LocalSpacing.current.small,
                    indicatorShape = CircleShape
                )
            }

            LaunchedEffect(key1 = pagerState.currentPage) {
                delay(6000)
                var newPosition = pagerState.currentPage + 1
                if (isFromProductDetail) {
                    if (newPosition > productDetailSliders.size - 1)
                        newPosition = 0
                } else {
                    if (newPosition > homeSliders.size - 1)
                        newPosition = 0
                }

                pagerState.scrollToPage(newPosition)
            }
        }
    }
}