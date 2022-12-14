package com.irv205.challengedecember.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.presentation.MainViewModel
import com.irv205.challengedecember.presentation.main.HeaderView

@Composable
fun DetailView(
    vm: MainViewModel,
    onBackHome: () -> Unit
){
    val hero = remember { vm.selectedHero }
    hero.value?.let {
        DetailBody(hero = it,
            onBackHome = { onBackHome.invoke() })
    }
}

@Composable
fun DetailBody(
    hero: Hero,
    modifier: Modifier = Modifier,
    onBackHome: () -> Unit
){
    Scaffold(modifier = modifier.fillMaxSize(), topBar = { HeaderView(title = hero.name) { onBackHome.invoke() } }) {
        println(it)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = hero.name)
        }
    }
}