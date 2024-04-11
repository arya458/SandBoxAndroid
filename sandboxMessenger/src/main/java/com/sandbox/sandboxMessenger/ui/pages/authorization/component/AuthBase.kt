package com.sandbox.sandboxMessenger.ui.pages.authorization.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.arya.danesh.coreui.AnimLogo
import com.arya.danesh.coreui.buttons.CustomIconButton
import com.arya.danesh.coreui.texts.TextSubTittle
import com.arya.danesh.coreui.theme.elv_3
import com.sandbox.sandboxmessenger.R


@Composable
@PreviewScreenSizes
fun AuthBase(content: @Composable (modifier: Modifier) -> Unit = {}) {



    Column(Modifier
            .fillMaxSize()
            .padding(
                    top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding(),
                    bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            ),
            Arrangement.Center,
            Alignment.CenterHorizontally) {

        Column(Modifier
                .fillMaxSize()
                .weight(0.3f)
                , Arrangement.Center, Alignment.CenterHorizontally) {

            AnimLogo(200.dp, shadowElevation = elv_3, logoID = R.drawable.icon, lottieAnimationID = R.raw.new_loading_anim)

        }
        content(Modifier
                .fillMaxSize()
                .weight(0.5f))

        Column(Modifier
                .fillMaxSize()
                .weight(0.2f), Arrangement.Center, Alignment.CenterHorizontally) {
            Row(Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), Arrangement.SpaceBetween, Alignment.CenterVertically) {

                Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f))
                Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .weight(0.2f)
                        .background(MaterialTheme.colorScheme.outline))
                TextSubTittle(
                        text = "Or Use",
                        modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.2f)
                                .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
                                .wrapContentHeight(),
                        textAlign = TextAlign.Center,
//                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.outline,
                )
                Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .weight(0.2f)
                        .background(MaterialTheme.colorScheme.outline))
                Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f))
            }

            Row(Modifier.wrapContentSize(), Arrangement.Center, Alignment.CenterVertically) {
                CustomIconButton(modifier = Modifier
                        .padding(5.dp)
                        .size(50.dp)
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(15.dp))
                        .clickable { },
                        border = BorderStroke(1.dp, Color(0xFFDB4437)),
                        imagePadding = 14.dp,
                        icon = R.drawable.google,
                        colorFilter = ColorFilter.tint( Color(0xFFDB4437)),
                        elevation = elv_3

                )
                CustomIconButton(modifier = Modifier
                        .padding(5.dp)
                        .size(50.dp)
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(15.dp))
                        .clickable { },
                        border = BorderStroke(1.dp, Color(0xFF0a66c2)),
                        imagePadding = 14.dp,
                        icon = R.drawable.linkin,
                        colorFilter = ColorFilter.tint(Color(0xFF0a66c2)),
                        elevation = elv_3
                )
                CustomIconButton(modifier = Modifier
                        .padding(5.dp)
                        .size(50.dp)
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(15.dp))
                        .clickable { },
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                        imagePadding = 14.dp,
                        icon = R.drawable.x,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                        elevation = elv_3
                )
            }


        }


    }


}