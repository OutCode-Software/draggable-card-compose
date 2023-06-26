package com.outcode.draggable_card_component

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.outcode.draggable_card.DraggableCard
import com.outcode.draggable_card_component.ui.theme.DraggablecardcomponentTheme

class MainActivity : ComponentActivity() {
    private val listOfDraggable = listOf(
        DraggableItems(image = "https://www.indiewire.com/wp-content/uploads/2018/02/moonlight-2016.jpg?w=800"),
        DraggableItems(image = "https://www.washingtonpost.com/graphics/2019/entertainment/oscar-nominees-movie-poster-design/img/green-book-web.jpg"),
        DraggableItems(image = "https://laughingsquid.com/wp-content/uploads/2016/01/martian1.jpg"),
        DraggableItems(image = "https://i.insider.com/5a6b6acc46a28823008b4570?width=750&format=jpeg&auto=webp"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DraggablecardcomponentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DraggableScreen()
                }
            }
        }
    }

    @Composable
    fun DraggableScreen() {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val cardHeight = screenHeight - 200.dp

        Surface(modifier = Modifier.fillMaxSize()) {
            val boxModifier = Modifier
            Box(
                modifier = boxModifier
                    .verticalGradientBackground(
                        listOf(
                            Color.White,
                            Color(0xFFFF6D63).copy(alpha = 0.2f)
                        )
                    )
            ) {
                listOfDraggable.forEachIndexed { index, draggableItem ->
                    Log.e("Check Item", draggableItem.image.toString())
                    DraggableCard(
                        item = draggableItem,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeight)
                            .padding(
                                top = 16.dp + (index + 2).dp,
                                bottom = 16.dp,
                                start = 16.dp,
                                end = 16.dp
                            ),
                        onSwiped = { _, _ ->
                            //do something with swiped item
                        }
                    ) {
                        CardContent(draggableItem)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun CardContent(item: DraggableItems) {
        Column {
            GlideImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

fun Modifier.verticalGradientBackground(
    colors: List<Color>
) = gradientBackground(colors) { gradientColors, size ->
    Brush.verticalGradient(
        colors = gradientColors,
        startY = 0f,
        endY = size.width
    )
}

fun Modifier.gradientBackground(
    colors: List<Color>,
    brushProvider: (List<Color>, Size) -> Brush
): Modifier = composed {
    var size by remember { mutableStateOf(Size.Zero) }
    val gradient = remember(colors, size) { brushProvider(colors, size) }
    drawWithContent {
        size = this.size
        drawRect(brush = gradient)
        drawContent()
    }
}

data class DraggableItems(
    val image: String,
)
