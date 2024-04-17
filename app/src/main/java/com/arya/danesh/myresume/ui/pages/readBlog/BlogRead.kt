package com.arya.danesh.myresume.ui.pages.readBlog

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.coreui.ErrorPage
import com.arya.danesh.coreui.SubLoadingPage
import com.arya.danesh.coreui.texts.TextTittle
import com.arya.danesh.myresume.R
import com.arya.danesh.myresume.data.response.PostResponse
import com.arya.danesh.myresume.di.viewModels.PostViewModel
import com.arya.danesh.myresume.ui.pages.readBlog.component.ImagePostCompose
import com.arya.danesh.myresume.ui.pages.readBlog.component.TextPostCompose
import com.arya.danesh.myresume.ui.pages.readBlog.component.VideoPostCompose
import com.arya.danesh.utilities.ResourceState
import com.arya.danesh.utilities.state.ReadState


@Composable
fun ReadBlog(
        navigateTo: (RootNavigation) -> Unit,
        postViewModel: PostViewModel = hiltViewModel()
) {

    val configuration = LocalConfiguration.current
    val readState = remember { mutableStateOf(ReadState.EXPENDED) }
    val lazyState = rememberLazyListState()
//    val systemBarsSize = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()

//    val isLaunched = remember { mutableStateOf(ComposeItemAnimationState.HIDDEN) }
//    LaunchedEffect(Unit) {
//        isLaunched.value = ComposeItemAnimationState.VISIBLE
////        snapshotFlow { readState.value }.collect { v ->
////            Log.d("tester", "ReadBlog: ${v.name}")
////            if (v == ReadState.FULL_IMAGE)
////                imageScrollState.animateScrollTo(imageScrollState.maxValue)
////            else
////                imageScrollState.animateScrollTo(imageScrollState.maxValue/2)
////        }
//
//    }

    val postRes by postViewModel.post.collectAsState()


    when (postRes) {

        is ResourceState.Loading -> {
            SubLoadingPage(isDark = isSystemInDarkTheme())
        }

        is ResourceState.Error -> {
            Log.d("ReadBlog", "ReadBlogERROR: " + (postRes as ResourceState.Error).error)
            ErrorPage((postRes as ResourceState.Error).error){
                postViewModel.tryAgain()
            }

        }

        is ResourceState.Success -> {
            val postData = (postRes as ResourceState.Success<PostResponse>).data
            val posts = postData.posts
            Log.d("ReadBlog", "ReadBlogDONE: " + postData.blogDate)




            if (lazyState.isScrollInProgress)
                if (lazyState.canScrollBackward) {

                    readState.value = ReadState.COLLAPSED
                } else {
                    readState.value = ReadState.EXPENDED
                }

            val transition = updateTransition(readState, label = "ReadAnim State")
            val sizeAnim = transition.animateDp(
                    transitionSpec = {
                        spring(
                                stiffness = 200f,
                                dampingRatio = 0.42f,
                        )
                    }, label = "color"


            ) { state ->

                when (state.value) {
                    ReadState.COLLAPSED -> 100.dp
                    ReadState.EXPENDED -> 400.dp
                    ReadState.FULL_IMAGE -> configuration.screenHeightDp.dp
                }


            }
            val blurAnim = transition.animateDp(
                    transitionSpec = {
                        spring(
                                stiffness = 200f,
                                dampingRatio = 0.42f,
                        )
                    }, label = "color"


            ) { state ->

                when (state.value) {
                    ReadState.COLLAPSED -> 30.dp
                    ReadState.EXPENDED -> 0.dp
                    ReadState.FULL_IMAGE -> 0.dp
                }


            }
//            val brightness = if (isSystemInDarkTheme()) -30f else 30f
//            //Image Size And Painter
//            val colorMatrix = floatArrayOf(
//                    1f, 0f, 0f, 0f, brightness,
//                    0f, 1f, 0f, 0f, brightness,
//                    0f, 0f, 1f, 0f, brightness,
//                    0f, 0f, 0f, 1f, 0f
//            )
            Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                Column(Modifier
                        .fillMaxSize()
                        .animateContentSize()) {
                    Surface(modifier = Modifier
                            .fillMaxWidth()
                            .height(sizeAnim.value)
                    )
                    {
                        AsyncImage(
                                postData.blogImage,
                                placeholder = painterResource(id = R.drawable.ph_images),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .defaultMinSize(configuration.screenWidthDp.dp)
                                        .fillMaxHeight()
                                        .blur(blurAnim.value)

                        )
//                        Column(Modifier
//                                .fillMaxSize()
//                                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)),
//                                Arrangement.Center,
//                                Alignment.CenterHorizontally) {
//                            TextTittle(
//                                    modifier = Modifier
//                                            .fillMaxWidth()
//                                            .wrapContentHeight(),
//                                    text = postData.blog_tittle,
//                                    color = androidx.compose.material.MaterialTheme.colors.onSurface,
//                                    textAlign = TextAlign.Center,
//                                    style = MaterialTheme.typography.titleLarge
//                            )
//                        }
                    }
                    Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight())
                }
                Column(Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()) {
                    Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(sizeAnim.value - 20.dp)
                    )

                    LazyColumn(
                            Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(color = MaterialTheme.colorScheme.surface, RoundedCornerShape(15.dp)),
                            contentPadding = PaddingValues(top = 20.dp, bottom = 140.dp),
                            state = lazyState,
                    ) {
                        item {
                            TextTittle(
                                    modifier = Modifier
                                            .padding(bottom = 30.dp, top = 10.dp)
                                            .fillMaxWidth()
                                            .wrapContentHeight(),
                                    text = postData.blogTittle,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center,
//                                    style = MaterialTheme.typography.titleLarge
                            )
                        }
                        items(posts.size) {

                            when (posts[it].type) {

                                "image" -> {
                                    ImagePostCompose(posts[it])
                                }

                                "text" -> {
                                    TextPostCompose(posts[it])
                                }
//                                "code"->{ CodePostCompose(posts[it]) }
                                "youtube" -> {
                                    VideoPostCompose(posts[it])
                                }

                            }

                        }

                    }

                }


            }

        }

    }


}

