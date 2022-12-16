package com.irv205.challengedecember.presentation.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.irv205.challengedecember.domain.model.Comics
import com.irv205.challengedecember.domain.model.Series

@Composable
fun MyLazyRowSeries(series: List<Series>) {
    val list = series
    LazyRow(
        modifier = Modifier
            .fillMaxWidth().padding(top = 8.dp)
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

@Composable
fun MyLazyRowComics(comics: List<Comics>) {
    val list = comics
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .fillMaxWidth().padding(top = 8.dp)
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
