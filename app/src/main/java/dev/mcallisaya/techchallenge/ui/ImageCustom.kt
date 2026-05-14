package dev.mcallisaya.techchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Precision
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.mcallisaya.techchallenge.R
import kotlinx.coroutines.Dispatchers

@Composable
fun ImageCustom(
    url: String,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.image_load))
    val progress by animateLottieCompositionAsState(composition, true)
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .dispatcher(Dispatchers.IO)
            .interceptorDispatcher(Dispatchers.Default)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(200)
            .size(width = 200, height = 200)
            .precision(Precision.INEXACT)
            .build()
    )

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
            }
            is AsyncImagePainter.State.Error -> {
                Image(
                    painter = painterResource(id = R.drawable.image_error),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            else -> {}
        }
    }
}