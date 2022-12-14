package com.irv205.challengedecember.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.presentation.MainViewModel
import com.irv205.challengedecember.presentation.main.HeaderView

@Composable
fun HomeView(
    vm: MainViewModel,
    onItemClick: () -> Unit
){
    val list = remember { vm.list }
    HomeBody(list = list,onItemClick = { onItemClick.invoke()
        vm.setHero(it) })
}

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    list: List<Hero> = emptyList(),
    onItemClick: (Hero) -> Unit
){
    LazyColumn(modifier = modifier){
        items(list) { item ->
            Divider(color = MaterialTheme.colors.onBackground)
            ItemList(hero = item){
                onItemClick(it)
            }
        }
    }
}

@Composable
fun ItemList(
    hero: Hero,
    onItemClick: (Hero) -> Unit
){
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    onItemClick(hero)
                },
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = hero.name)
            //Text(text = hero.thumbnail)
        }
    }
}