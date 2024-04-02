package com.arya.danesh.myresume.ui.pages.main.sub.skills

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arya.danesh.controller.route.RootNavigation
import com.arya.danesh.myresume.data.response.BlogResponse
import com.arya.danesh.myresume.data.response.SkillResponse
import com.arya.danesh.myresume.di.viewModels.SkillViewModel
import com.arya.danesh.utilities.state.ComposeItemAnimationState
import com.arya.danesh.myresume.ui.pages.main.component.SubMainBase
import com.arya.danesh.myresume.ui.pages.main.sub.skills.component.SkillsCompose
import com.arya.danesh.utilities.ResourceState

@Composable
fun SkillsPage(
        isCollapseListener: (Boolean, Boolean) -> Unit,
        navigateTo: (RootNavigation) -> Unit,
        skillViewModel: SkillViewModel= hiltViewModel()
) {
    val skillRes by skillViewModel.skill.collectAsState()

    when (skillRes) {

        is ResourceState.Loading -> {
            Text(text = "Loading", Modifier.fillMaxSize(), textAlign = TextAlign.Center)
        }

        is ResourceState.Error -> {
            Text(text = (skillRes as ResourceState.Error).error,
                    Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center)

        }

        is ResourceState.Success -> {
            SubMainBase(isCollapseListener)
            { lazyState, visibleItems, _ ->
                val skills = (skillRes as ResourceState.Success<SkillResponse>).data.skills
                LazyColumn(
                        Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(top = 20.dp, bottom = 140.dp),
                        state = lazyState,

                        ) {
                    items(skills.size) {
                        SkillsCompose(
                                {},
                                it,
                                skills[it]) { itemNumber ->
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
    }


}