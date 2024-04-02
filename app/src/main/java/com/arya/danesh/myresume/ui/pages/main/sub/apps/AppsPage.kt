package com.arya.danesh.myresume.ui.pages.main.sub.apps

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
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.myresume.data.response.AppResponse
import com.arya.danesh.myresume.di.viewModels.AppsViewModel
import com.arya.danesh.myresume.ui.pages.main.component.SubMainBase
import com.arya.danesh.myresume.ui.pages.main.sub.apps.component.AppsCompose
import com.arya.danesh.utilities.ResourceState
import com.arya.danesh.utilities.state.ComposeItemAnimationState

@Composable
fun AppsPage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (RootNavigation) -> Unit,
        appsViewModel: AppsViewModel = hiltViewModel()
) {
    val appsRes by appsViewModel.apps.collectAsState()

    when (appsRes) {

        is ResourceState.Loading -> {
            Text(text = "Loading", Modifier.fillMaxSize(), textAlign = TextAlign.Center)
        }

        is ResourceState.Error -> {
            Text(text = (appsRes as ResourceState.Error).error,
                    Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center)

        }

        is ResourceState.Success -> {
            val apps = (appsRes as ResourceState.Success<AppResponse>).data.apps

            SubMainBase(isCollapseListener)
            { lazyState, visibleItems, _ ->
                LazyColumn(
                        Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(top = 20.dp, bottom = 140.dp),
                        state = lazyState,

                        ) {
                    items(apps.size) {
                        AppsCompose(app = apps.get(it) , size = it) { itemNumber ->
                            if (visibleItems.visibleItemsInfo.isNotEmpty())
                                if (visibleItems.visibleItemsInfo.first().index <= itemNumber && itemNumber <= visibleItems.visibleItemsInfo.last().index + 1)
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

        else -> {}
    }

}