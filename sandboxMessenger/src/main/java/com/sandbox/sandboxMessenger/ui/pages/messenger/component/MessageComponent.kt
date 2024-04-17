package com.sandbox.sandboxMessenger.ui.pages.messenger.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.arya.danesh.coreui.texts.TextCaption
import com.arya.danesh.coreui.theme.elv_3
import com.arya.danesh.utilities.CoreUtility.MATRIX_TRAGET_USER
import com.sandbox.sandboxmessenger.R
import net.folivo.trixnity.core.model.events.ClientEvent
import net.folivo.trixnity.core.model.events.MessageEventContent
import net.folivo.trixnity.core.model.events.m.room.RoomMessageEventContent
import net.folivo.trixnity.core.model.events.senderOrNull


@Composable
fun MessageView(item: ClientEvent<MessageEventContent>, supportImage: Bitmap?, userImage: Bitmap?) {
    val isSupport: Boolean = item.senderOrNull?.full.toString().contains(MATRIX_TRAGET_USER)
    val content = (item.content as RoomMessageEventContent).body
    if (isSupport)
        SupportMessage(text = content, supportImage)
    else
        UserMessage(text = content, userImage)
}


@Composable
private fun SupportMessage(text: String, supportImage: Bitmap?) {


    Column(Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(), Arrangement.Top, Alignment.Start) {
//        if (item.isLastMessageSenderTheSame)
            Image(
                    bitmap = (supportImage?.asImageBitmap()
                            ?: ImageBitmap.imageResource(R.drawable.ph_profile)),
                    contentDescription = "",
                    Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .shadow(elv_3, clip = true),
                    contentScale = ContentScale.Crop
            )
        Surface(modifier = Modifier
                .fillMaxWidth(0.6f)
                .wrapContentHeight()
                .padding(10.dp),
                shadowElevation = elv_3,
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(
                        10.dp
                )
        ) {
            TextCaption(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp), text = text)


        }

    }

}

@Composable
private fun UserMessage(text: String, userImage: Bitmap?) {

    Column(Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(), Arrangement.Top, Alignment.End) {
            Image(
                    bitmap = (userImage?.asImageBitmap()
                            ?: ImageBitmap.imageResource(R.drawable.ph_profile)),
                    contentDescription = "",
                    Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .shadow(elv_3, clip = true),
                    contentScale = ContentScale.Crop
            )
        Surface(modifier = Modifier
                .fillMaxWidth(0.6f)
                .wrapContentHeight()
                .padding(10.dp),
                shadowElevation = elv_3,
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(
                        10.dp
                )
        ) {
            TextCaption(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp), text = text)


        }

    }

}
