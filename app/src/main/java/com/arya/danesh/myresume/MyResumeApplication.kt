package com.arya.danesh.myresume

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyResumeApplication :Application(){

    companion object {

        const val TAG = "MyResume"

    }


}