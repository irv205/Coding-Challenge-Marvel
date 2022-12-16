package com.irv205.challengedecember.presentation.home

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.irv205.challengedecember.domain.model.Comics
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.domain.model.Series
import com.irv205.challengedecember.presentation.MainViewModel
import com.irv205.challengedecember.presentation.ui.theme.SecondaryColor

@Composable
fun HomeView(
    vm: MainViewModel,
    onItemClick: () -> Unit
) {
    val list = remember { vm.list }
    val listComics = remember { vm.listComics }
    val listSeries = remember { vm.listSeries }
    HomeBody(
        list = list, series = listSeries, comics = listComics, viewModel = vm,
        onItemClick = {
            onItemClick.invoke()
            vm.setHero(it)
        },
    )
}

@Composable
fun HomeBody(
    series: List<Series>,
    comics: List<Comics>,
    modifier: Modifier = Modifier,
    list: List<Hero> = emptyList(),
    viewModel: MainViewModel,
    onItemClick: (Hero) -> Unit,

    ) {

    var selectedIndex by remember { mutableStateOf(-1) }
    var selectedPosition by remember { mutableStateOf(true) }

    val onItemClick = { index: Int, position: Boolean ->
        viewModel.getSeriesList(list.get(index).id)
        viewModel.getComicsList(list.get(index).id)
        if (selectedIndex == index)
            selectedIndex = -1
        else
            selectedIndex = index

        selectedPosition = position
    }

    val state = rememberLazyListState()
    val elements = mutableListOf<Hero>()
    val configuration = LocalConfiguration.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn(modifier = modifier) {
            itemsIndexed(list) { index, item ->
                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        MyCardLandScape(
                            item,
                            index,
                            1,
                            state,
                            series,
                            comics,
                            selectedIndex,
                            onItemClick
                        )
                    }
                    else -> {
                        MyCard(item, index, 1, state, series, comics, selectedIndex, onItemClick)
                    }
                }

            }
        }
    }
}


@Composable
fun MyCard(
    character: Hero,
    index: Int = 0,
    columns: Int = 1,
    state: LazyListState,
    series: List<Series>,
    comics: List<Comics>,
    selectedIndex: Int,
    onClick: (Int, Boolean) -> Unit
) {

    //Animation Card
    val (delay, easing) = state.calculateDelayAndEasing(index, columns)
    val animation = tween<Float>(durationMillis = 500, delayMillis = delay, easing = easing)
    val args = ScaleAndAlphaArgs(fromScale = 10f, toScale = 1f, fromAlpha = 0f, toAlpha = 1f)
    val (scale, alpha) = scaleAndAlpha(args = args, animation = animation)

    val argsRotation = RotationArgs(fromRotation = 80f, toRotation = 0f)
    val animationRotate = tween<Float>(durationMillis = 1300, delayMillis = delay, easing = easing)
    val rotation = rotation(argsRotation, animationRotate)

    val animationText = tween<Float>(durationMillis = 500, delayMillis = delay, easing = easing)
    val argsText = ScaleAndAlphaArgs(fromScale = 400f, toScale = 1f, fromAlpha = 0f, toAlpha = 1f)
    val (scaleText, alphaText) = scaleAndAlpha(args = argsText, animation = animationText)


    Card(
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(if (selectedIndex == index) 700.dp else 320.dp)
            .graphicsLayer(
                alpha = alpha,
                scaleX = scale,
                scaleY = scale,
                rotationX = rotation,
                rotationY = 0.0F,
                rotationZ = 0.0F
            )
            .animateContentSize(
                animationSpec = TweenSpec(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing,

                    )
            )
            .clickable {
                onClick.invoke(index, true)
            },
    ) {

        Box {

            Image(
                painter = rememberAsyncImagePainter(character.thumbnail),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .fillMaxWidth()
                    .fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(2.0F)
                    .animateContentSize(
                        animationSpec = TweenSpec(
                            durationMillis = 500,
                            easing = LinearOutSlowInEasing,

                            )
                    )
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colors.surface.copy(alpha = 0.7F),
                                MaterialTheme.colors.surface,
                            )
                        )
                    )
                    .zIndex(1.0F)
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    character.url?.get(0)?.let {
                        MyIconButon(it)
                    }
                }


                Text(
                    text = character.name,
                    modifier = Modifier
                        .padding(top = 200.dp, start = 8.dp)
                        .graphicsLayer(cameraDistance = scaleText)
                        .animateContentSize(
                            animationSpec = TweenSpec(
                                durationMillis = 500,
                                easing = LinearOutSlowInEasing,

                                )
                        )
                        .background(Color.Transparent),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Box(Modifier.padding(8.dp)) {
                    Divider(
                        Modifier
                            .height(6.dp)
                            .width(40.dp)
                            .background(SecondaryColor)
                            .padding(start = 12.dp, top = 4.dp)
                    )
                }

                Text(
                    text = character.description?.take(100) ?: "",
                    modifier = Modifier.padding(top = 12.dp, start = 8.dp),
                    fontSize = 15.sp
                )

                Text(
                    text = "Series",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                MyLazyRowSeries(series)

                Text(
                    text = "Comics",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                MyLazyRowComics(comics)

                //MyButton(character.series)
            }


        }


    }
}

@Composable
fun MyIconButon(url : String) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(url)) }

    Icon(
        Icons.Filled.Info,
        "Details",
        modifier = Modifier
            .size(50.dp)
            .padding(top = 12.dp, end = 8.dp).clickable {
                context.startActivity(intent)
            },
    )
}


@Composable
fun MyCardLandScape(
    character: Hero,
    index: Int = 0,
    columns: Int,
    state: LazyListState,
    series: List<Series>,
    comics: List<Comics>,
    selectedIndex: Int,
    onClick: (Int, Boolean) -> Unit
) {

    //Animation Card
    val (delay, easing) = state.calculateDelayAndEasing(index, columns)
    val animation = tween<Float>(durationMillis = 500, delayMillis = delay, easing = easing)
    val args = ScaleAndAlphaArgs(fromScale = 10f, toScale = 1f, fromAlpha = 0f, toAlpha = 1f)
    val (scale, alpha) = scaleAndAlpha(args = args, animation = animation)

    val argsRotation = RotationArgs(fromRotation = 80f, toRotation = 0f)
    val animationRotate = tween<Float>(durationMillis = 1300, delayMillis = delay, easing = easing)
    val rotation = rotation(argsRotation, animationRotate)

    val animationText = tween<Float>(durationMillis = 500, delayMillis = delay, easing = easing)
    val argsText = ScaleAndAlphaArgs(fromScale = 400f, toScale = 1f, fromAlpha = 0f, toAlpha = 1f)
    val (scaleText, alphaText) = scaleAndAlpha(args = argsText, animation = animationText)


    Card(
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(320.dp)
            //.graphicsLayer(alpha = alpha, scaleX = scale, scaleY = scale)
            .graphicsLayer(
                alpha = alpha,
                scaleX = scale,
                scaleY = scale,
                rotationX = rotation,
            )
            .animateContentSize(
                animationSpec = TweenSpec(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing,

                    )
            )
            .clickable {
                onClick.invoke(index, true)
            },
    ) {
        Box {

            Row {
                Image(
                    painter = rememberAsyncImagePainter(character.thumbnail),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .height(550.dp)
                        .fillMaxWidth(0.4F)
                        .padding(end = 8.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .size(700.dp)
                        .zIndex(2.0F)
                        .animateContentSize(
                            animationSpec = TweenSpec(
                                durationMillis = 500,
                                easing = LinearOutSlowInEasing,

                                )
                        )
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colors.surface.copy(alpha = 0.7F),
                                    MaterialTheme.colors.surface,

                                    )
                            )
                        )
                        .zIndex(1.0F)
                        .height(700.dp)
                        .fillMaxWidth(0.6F)
                ) {
                    item {
                        Text(
                            text = character.name,
                            modifier = Modifier
                                .padding(top = 20.dp, start = 8.dp)
                                .graphicsLayer(cameraDistance = scaleText)
                                .animateContentSize(
                                    animationSpec = TweenSpec(
                                        durationMillis = 500,
                                        easing = LinearOutSlowInEasing,

                                        )
                                )
                                .background(Color.Transparent),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item {
                        Box(Modifier.padding(8.dp)) {
                            Divider(
                                Modifier
                                    .height(6.dp)
                                    .width(40.dp)
                                    .background(SecondaryColor)
                                    .padding(start = 12.dp, top = 4.dp)
                            )
                        }
                    }
                    item {
                        Text(
                            text = character.description?.take(100) ?: "",
                            modifier = Modifier.padding(top = 12.dp, start = 8.dp),
                            fontSize = 15.sp
                        )
                    }
                    item {
                        Text(
                            text = "Series",
                            modifier = Modifier.padding(8.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item {
                        MyLazyRowSeries(series)
                    }
                    item {
                        Text(
                            text = "Comics",
                            modifier = Modifier.padding(8.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item {
                        MyLazyRowComics(comics)
                    }
                }
            }
        }
    }
}


@Composable
private fun LazyListState.calculateDelayAndEasing(index: Int, columnCount: Int): Pair<Int, Easing> {
    val row = index / columnCount
    val column = index % columnCount
    val firstVisibleRow = firstVisibleItemIndex
    val visibleRows = layoutInfo.visibleItemsInfo.count()
    val scrollingToBottom = firstVisibleRow < row
    val isFirstLoad = visibleRows == 0
    val rowDelay = 100 * when {
        isFirstLoad -> row // initial load
        scrollingToBottom -> visibleRows + firstVisibleRow - row // scrolling to bottom
        else -> 1 // scrolling to top
    }
    val scrollDirectionMultiplier = if (scrollingToBottom || isFirstLoad) 1 else -1
    val columnDelay = column * 100 * scrollDirectionMultiplier
    val easing =
        if (scrollingToBottom || isFirstLoad) LinearOutSlowInEasing else FastOutSlowInEasing
    return rowDelay + columnDelay to easing
}


@Composable
fun scaleAndAlpha(
    args: ScaleAndAlphaArgs,
    animation: FiniteAnimationSpec<Float>
): Pair<Float, Float> {
    val transitionState =
        remember { MutableTransitionState(State.PLACING).apply { targetState = State.PLACED } }
    val transition = updateTransition(transitionState)
    val alpha by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            State.PLACING -> args.fromAlpha
            State.PLACED -> args.toAlpha
        }
    }
    val scale by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            State.PLACING -> args.fromScale
            State.PLACED -> args.toScale
        }
    }
    return alpha to scale
}

@Composable
fun rotation(
    args: RotationArgs,
    animation: FiniteAnimationSpec<Float>
): Float {
    val transitionState =
        remember { MutableTransitionState(State.PLACING).apply { targetState = State.PLACED } }
    val transition = updateTransition(transitionState)

    val rotation by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            State.PLACING -> args.fromRotation
            State.PLACED -> args.toRotation
        }
    }
    return rotation
}

private enum class State { PLACING, PLACED }

data class ScaleAndAlphaArgs(
    val fromScale: Float,
    val toScale: Float,
    val fromAlpha: Float,
    val toAlpha: Float
)

data class RotationArgs(
    val fromRotation: Float,
    val toRotation: Float,
)


@Composable
fun MyButton(resourceURI: String?) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(resourceURI)) }

    Button(onClick = { context.startActivity(intent) }) {
        Text(text = "Details")
    }
}


@Composable
fun MyLazyRowComics(comics: List<Comics>) {
    val list = comics
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        items(items = list, itemContent = { item ->
            Box(
                Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Column(
                    modifier = Modifier
                        .size(120.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Image(
                        painter = rememberAsyncImagePainter(item.thumbnail),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .fillMaxWidth()
                    )
                    Text(
                        text = item.name,
                        modifier = Modifier.padding(4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        })
    }
}

@Composable
fun MyLazyRowSeries(series: List<Series>) {
    val list = series
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        items(items = list, itemContent = { item ->
            Box(
                Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Column(
                    modifier = Modifier
                        .size(120.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Image(
                        painter = rememberAsyncImagePainter(item.thumbnail),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .fillMaxWidth()
                    )
                    Text(
                        text = item.name,
                        modifier = Modifier.padding(4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        })
    }
}
