package com.irv205.challengedecember.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.irv205.challengedecember.R
import com.irv205.challengedecember.domain.model.Comics
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.domain.model.Series
import com.irv205.challengedecember.presentation.MainViewModel
import com.irv205.challengedecember.presentation.componets.GifImage
import com.irv205.challengedecember.presentation.componets.MyCard
import com.irv205.challengedecember.presentation.componets.MyCardLandScape
import com.irv205.challengedecember.presentation.ui.theme.PrimaryColor


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

    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = PrimaryColor
    )

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
    val configuration = LocalConfiguration.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val search = remember { viewModel.search.value }

        Column {
            Row {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.8F)
                        .padding(top = 12.dp, bottom = 10.dp, start = 8.dp, end = 8.dp)
                        .clip(RoundedCornerShape(18.dp)),
                    value = viewModel.search.value.toString(),
                    onValueChange = { it ->
                        viewModel.updateSearchText(it)
                    },
                    placeholder = { Text(text = stringResource(R.string.hint_search_query)) },
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.subtitle1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Filled.Search,
                        stringResource(R.string.Details),
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                viewModel.getHeroesList()
                            },
                    )
                }


            }

            if (list.isEmpty()) {
                GifImage()
            } else {
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
                                MyCard(
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
                        }
                    }
                }
            }
        }
    }
}








