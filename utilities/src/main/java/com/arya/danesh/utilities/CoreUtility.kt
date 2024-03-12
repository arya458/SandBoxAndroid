package com.arya.danesh.utilities

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import androidx.window.layout.WindowMetricsCalculator

object CoreUtility {



    var key :String=""
    var messengerToken :String=""
    const val APP_BASE_URL = "https://aryajsonbucket.4everland.store/"
    const val CHAT_BASE_URL = "https://matrix.org"
    const val MATRIX_TRAGET_USER = "aria.danesh"
    const val MATRIX_TARGET_DOMAIN = "matrix.org"



    var screenWidth = 0.dp

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
                .map { allowedChars.random() }
                .joinToString("")
    }



    fun isInternetConnected(context : Context):Boolean{

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork?:return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        val result = when{
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
        return result
    }

    fun computeWindowSize(activity: Activity) {
        val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)
        screenWidth = metrics.bounds.width().dp
//        val height = metrics.bounds.height()
//        val density = resources.displayMetrics.density
//        val windowSizeClass = WindowSizeClass.compute(width/density, height/density)
//        // COMPACT, MEDIUM, or EXPANDED
//        val widthWindowSizeClass = windowSizeClass.windowWidthSizeClass
//        // COMPACT, MEDIUM, or EXPANDED
//        val heightWindowSizeClass = windowSizeClass.windowHeightSizeClass

        // Use widthWindowSizeClass and heightWindowSizeClass.
    }

    fun dpToPx(dp: Int):Float{
        return  dp * Resources.getSystem().displayMetrics.density
    }

}