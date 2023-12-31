package com.arya.danesh.myresume

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arya.danesh.myresume.ui.theme.MyResumeTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()


            MyResumeTheme(darkTheme = true, dynamicColor = false ) {

                NavHost(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    navController = navController,
                    startDestination = "Into"
                ) {
                    intoGraph() {


                        //todo: UnComment Later We Need PopBackStack (For know we need to Debug)
//                        navController.popBackStack()
                        navController.navigate(it.route)
                    }
                }
            }
//                Main()

        }

    }
}