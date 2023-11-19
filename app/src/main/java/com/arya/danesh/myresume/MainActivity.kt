package com.arya.danesh.myresume

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.arya.danesh.myresume.pages.sections.NavBar.navBar
import com.arya.danesh.myresume.pages.sections.Toolbar.ToolBar
import com.arya.danesh.myresume.ui.theme.MyResumeTheme
import com.arya.danesh.myresume.ui.theme.appDark
import com.arya.danesh.myresume.ui.theme.appLight

class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val state = rememberScrollState()
            val currentPage = remember { mutableStateOf("home") }
            val isExpended = remember { mutableStateOf(true) }

            MyResumeTheme(darkTheme = true) {
                // A surface container using the 'background' color from the theme


                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    appDark,
                                    appLight
                                )
                            )
                        ),
                    backgroundColor = Color.Transparent,
                    scaffoldState = rememberScaffoldState(),
                    topBar = {
                        ToolBar(state,currentPage,isExpended)
                    },
                    bottomBar = { navBar(navHostController = navController, currentPage,isExpended) },
                    content = {
                        pageController(navController = navController,currentPage)
                    }
                )


            }

        }

    }
}