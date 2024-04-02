package com.arya.danesh.myresume.ui.pages.readBlog.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arya.danesh.coreui.Texts.TextTittle
import com.arya.danesh.myresume.data.response.PostText


@Composable
fun ImagePostCompose(postText: PostText) {
    
    Column(Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
            Arrangement.Center,
            Alignment.CenterHorizontally
    ) {

        TextTittle(
                modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                text = postText.tittle,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(10.dp))
        AsyncImage(
                model = postText.data,
                contentDescription = null,
                modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.FillWidth,



        )

    }

}