package com.arya.danesh.myresume

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.arya.danesh.myresume.ui.pages.rootPages.Landing
import com.arya.danesh.myresume.ui.theme.MyResumeTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()


            MyResumeTheme(darkTheme = true, dynamicColor = false ) {
                Landing(navController)
            }
//                Main()

        }

    }
}