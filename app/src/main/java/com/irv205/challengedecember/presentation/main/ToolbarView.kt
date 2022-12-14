package com.irv205.challengedecember.presentation.main

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun HeaderView(title: String, backNavigation: () -> Unit){
    TopAppBar(title = { Text(text = title) }, elevation = 4.dp)
}