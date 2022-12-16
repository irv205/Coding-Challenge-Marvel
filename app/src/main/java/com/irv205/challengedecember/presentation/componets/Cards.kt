package com.irv205.challengedecember.presentation.componets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.irv205.challengedecember.R
import com.irv205.challengedecember.core.utils.animation.RotationArgs
import com.irv205.challengedecember.core.utils.animation.ScaleAndAlphaArgs
import com.irv205.challengedecember.core.utils.animation.StateAnimation
import com.irv205.challengedecember.domain.model.Comics
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.domain.model.Series
import com.irv205.challengedecember.presentation.home.*
import com.irv205.challengedecember.presentation.ui.theme.PrimaryColor
import com.irv205.challengedecember.presentation.ui.theme.SecondaryColor


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
                    character.url.get(0).let {
                        MyIconButon(it)
                    }
                }


                Text(

                    text = character.name,
                    modifier = Modifier
                        .padding(top = 190.dp, start = 8.dp)
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

                Box(Modifier.padding(start = 8.dp)) {
                    Divider(
                        Modifier
                            .height(4.dp)
                            .width(40.dp)
                            .background(PrimaryColor)
                            .padding(start = 12.dp, top = 8.dp)
                    )
                }

                Text(
                    text = character.description?.take(100) ?: "",
                    modifier = Modifier.padding(top = 12.dp, start = 8.dp),
                    fontSize = 15.sp
                )

                Text(
                    text = stringResource(R.string.Series),
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp,end= 8.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(Modifier.padding(start= 8.dp)) {
                    Divider(
                        Modifier
                            .height(2.dp)
                            .width(40.dp)
                            .background(PrimaryColor)
                    )
                }

                MyLazyRowSeries(series)

                Text(
                    text = stringResource(R.string.Comics),
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp,end= 8.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Box(Modifier.padding(start=8.dp)) {
                    Divider(
                        Modifier
                            .height(2.dp)
                            .width(40.dp)
                            .background(PrimaryColor)
                    )
                }

                MyLazyRowComics(comics)

                //MyButton(character.series)
            }


        }


    }
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
                                .padding(top = 20.dp, start = 8.dp, bottom = 4.dp)
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
                            text =  stringResource(id = R.string.Series),
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
                            text = stringResource(id = R.string.Comics),
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
        remember { MutableTransitionState(StateAnimation.PLACING).apply { targetState = StateAnimation.PLACED } }
    val transition = updateTransition(transitionState)
    val alpha by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            StateAnimation.PLACING -> args.fromAlpha
            StateAnimation.PLACED -> args.toAlpha
        }
    }
    val scale by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            StateAnimation.PLACING -> args.fromScale
            StateAnimation.PLACED -> args.toScale
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
        remember { MutableTransitionState(StateAnimation.PLACING).apply { targetState = StateAnimation.PLACED } }
    val transition = updateTransition(transitionState)

    val rotation by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            StateAnimation.PLACING -> args.fromRotation
            StateAnimation.PLACED -> args.toRotation
        }
    }
    return rotation
}
