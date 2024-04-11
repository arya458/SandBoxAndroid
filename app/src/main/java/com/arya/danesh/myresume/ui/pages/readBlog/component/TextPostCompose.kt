package com.arya.danesh.myresume.ui.pages.readBlog.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arya.danesh.coreui.texts.AnimText
import com.arya.danesh.coreui.texts.TextSubTittle
import com.arya.danesh.myresume.data.response.PostText
import com.arya.danesh.utilities.state.ComposeItemAnimationState


@Composable
fun TextPostCompose(postText: PostText) {



    Column(Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
            Arrangement.Center,
            Alignment.CenterHorizontally
    ) {

        TextSubTittle(
                modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                text = postText.tittle,
                color =MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(10.dp))
        AnimText(
                rawText = postText.data,
                setAnimState = {it.value = ComposeItemAnimationState.VISIBLE},
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .wrapContentHeight(),
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
//                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(0.6f),
                durationMillis = 2000,
                delayMillis = 500
        )

    }


}