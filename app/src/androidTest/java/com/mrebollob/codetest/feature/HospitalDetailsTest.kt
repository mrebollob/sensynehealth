package com.mrebollob.codetest.feature

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.idle
import com.mrebollob.codetest.presentation.features.list.MainActivity
import com.mrebollob.codetest.screen.DetailsScreen
import com.mrebollob.codetest.screen.ListScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HospitalDetailsTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    val appListScreen = ListScreen()
    val appDetailsScreen = DetailsScreen()

    @Test
    fun when_click_in_an_hospital_item_show_hospital_details() {
        appListScreen {

            // wait for the loading
            idle(3000)

            navigateToFirstItem()
        }

        appDetailsScreen {
            idle(1000)
            titleView.isVisible()
        }
    }
}