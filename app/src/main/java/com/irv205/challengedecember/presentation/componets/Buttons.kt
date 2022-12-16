package com.irv205.challengedecember.presentation.componets

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.irv205.challengedecember.R

@Composable
fun MyIconButon(url: String) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(url)) }

    Icon(
        Icons.Filled.Info,
        stringResource(R.string.Details),
        modifier = Modifier
            .size(50.dp)
            .padding(top = 12.dp, end = 8.dp)
            .clickable {
                context.startActivity(intent)
            },
    )
}


@Composable
fun MyButton(resourceURI: String?) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(resourceURI)) }

    Button(onClick = { context.startActivity(intent) }) {
        Text(text = "Details")
    }
}