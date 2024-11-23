package com.abanapps.book.presentation.book_detail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import bookshelf.composeapp.generated.resources.Res
import bookshelf.composeapp.generated.resources.arrow_back
import bookshelf.composeapp.generated.resources.book_cover
import bookshelf.composeapp.generated.resources.book_error_2
import bookshelf.composeapp.generated.resources.remove_from_favourite
import coil3.compose.rememberAsyncImagePainter
import com.abanapps.core.presentation.DarkBlue
import com.abanapps.core.presentation.DesertWhite
import com.abanapps.core.presentation.SandYellow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BlurredImageBackground(
    imageUrl: String?,
    isFavourite: Boolean,
    onFavouriteClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }

    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        onSuccess = {
            val size = it.painter.intrinsicSize
            imageLoadResult = if (size.width > 1 && size.height > 1) {
                Result.success(it.painter)
            } else {
                Result.failure(Exception("Invalid Image dimension"))
            }
        }
    )

    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier.weight(.3f).fillMaxWidth().background(DarkBlue)) {

                imageLoadResult?.getOrNull()?.let { painter ->

                    Image(
                        painter = painter,
                        contentDescription = stringResource(Res.string.book_cover),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize().blur(20.dp),
                    )

                }


            }

            Box(modifier = Modifier.weight(.7f).fillMaxWidth().background(DesertWhite))


        }

        IconButton(
            onClick = {
                onBackClick()
            },
            modifier = Modifier.align(Alignment.TopStart).padding(top = 16.dp, start = 16.dp)
                .statusBarsPadding()
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(Res.string.arrow_back),
                tint = Color.White
            )


        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.fillMaxHeight(0.15f))

            ElevatedCard(
                modifier = Modifier.height(270.dp).aspectRatio(2 / 3f),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.Transparent),
                elevation = CardDefaults.elevatedCardElevation(15.dp)
            ) {
                AnimatedContent(targetState = imageLoadResult) { result ->

                    when (result) {

                        null -> CircularProgressIndicator()
                        else -> {

                            Box {
                                Image(
                                    painter = if (result.isSuccess) painter else painterResource(Res.drawable.book_error_2),
                                    contentDescription = stringResource(Res.string.book_cover),
                                    modifier = Modifier.fillMaxSize().background(Color.Transparent),
                                    contentScale = if (result.isSuccess) {
                                        ContentScale.Crop
                                    } else {
                                        ContentScale.Fit
                                    }
                                )

                                IconButton(
                                    onClick = {
                                        onFavouriteClick()
                                    },
                                    modifier = Modifier.align(Alignment.BottomEnd)
                                ) {
                                    Icon(
                                        imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                        contentDescription = if (isFavourite) stringResource(Res.string.remove_from_favourite) else stringResource(
                                            Res.string.remove_from_favourite
                                        ),
                                        tint = Color.Red,

                                    )
                                }
                            }


                        }

                    }

                }
            }

            content()

        }


    }

}
