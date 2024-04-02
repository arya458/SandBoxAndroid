package com.arya.danesh.coreui.Buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CustomIconButton(
        modifier: Modifier,
        imagePadding: Dp,
        border: BorderStroke? = null,
        @DrawableRes icon: Int,
        colorFilter: ColorFilter? = null,
        elevation: Dp = 0.dp) {

    Surface(
            modifier
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(15.dp)),
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(15.dp),
            border = border,
            elevation = elevation
    ) {
        Image(
                painter = painterResource(icon),
                contentDescription = "",
                Modifier
                        .fillMaxSize()
                        .padding(imagePadding)
                        .background(Color.Transparent),
                colorFilter = colorFilter,
                contentScale = ContentScale.Inside

        )

    }


}