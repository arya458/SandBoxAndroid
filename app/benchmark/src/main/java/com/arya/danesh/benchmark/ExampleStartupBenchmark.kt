package com.arya.danesh.benchmark

import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
            packageName = "com.arya.danesh.myresume",
            metrics = listOf(StartupTimingMetric()),
            iterations = 1,
            startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

        val loadingPass = device.findObject(By.descContains("LoadingPass"))
        loadingPass.click()

        val loginPass = device.findObject(By.text("Login"))
        loginPass.click()


        pageTester(device,"NaviButton News")
        pageTester(device,"NaviButton Home")
        pageTester(device,"NaviButton Apps")
        pageTester(device,"NaviButton Skills")


//        val Messenger = device.findObject(By.descContains("NaviButton Messenger"))
//        Messenger.click()
//        device.pressBack()
//        device.pressBack()

    }
    fun pageTester(device:UiDevice,subString: String){
        val page = device.findObject(By.descContains(subString))
        page.click()
        device.swipe(0,device.displayHeight/2,0,device.displayHeight,100)
        device.swipe(0,device.displayHeight,0,device.displayHeight/2,100)
    }




}


