package com.arya.danesh.myresume.ui.pages.main.sub.blog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.myresume.di.viewModels.BlogViewModel
import com.arya.danesh.myresume.ui.controller.route.RootNavigation
import com.arya.danesh.myresume.ui.core.state.ComposeItemAnimationState
import com.arya.danesh.myresume.ui.pages.main.component.SubMainBase
import com.arya.danesh.myresume.ui.pages.main.sub.blog.component.BlogCompose
import com.arya.danesh.utilities.CoreUtility.key
import com.arya.danesh.utilities.ResourceState

@Composable
fun BlogPage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (RootNavigation) -> Unit,
        blogViewModel: BlogViewModel = hiltViewModel()
) {

    val blogRes by blogViewModel.blog.collectAsState()

    when (blogRes) {

        is ResourceState.Loading -> {
            Text(text = "Loading", Modifier.fillMaxSize(), textAlign = TextAlign.Center)
        }

        is ResourceState.Error -> {
            Text(text = (blogRes as ResourceState.Error).error,
                    Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center)

        }

        is ResourceState.Success -> {

            SubMainBase(isCollapseListener)
            { lazyState, visibleItems, listener ->
                LazyColumn(
                        Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(top = 20.dp, bottom = 140.dp),
                        state = lazyState,
                ) {
                    val posts = (blogRes as ResourceState.Success<BlogResponse>).data.blog
                    items(posts.size) {

                        BlogCompose(onclick = {
                            key=posts[it].id.toString()
                            navigateTo(RootNavigation.Root.ReadBlog)
                        }, size = it, posts[it]) { itemNumber ->
                            if (visibleItems.visibleItemsInfo.isNotEmpty())
                                if (visibleItems.visibleItemsInfo.first().index <= itemNumber && itemNumber <= visibleItems.visibleItemsInfo.last().index)
                                    ComposeItemAnimationState.VISIBLE
                                else
                                    ComposeItemAnimationState.HIDDEN
                            else
                                ComposeItemAnimationState.HIDDEN
                        }

                    }
                }
            }

        }


    }


}