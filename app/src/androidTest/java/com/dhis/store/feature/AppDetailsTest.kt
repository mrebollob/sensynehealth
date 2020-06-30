package com.dhis.store.feature

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.idle
import com.dhis.store.presentation.features.list.MainActivity
import com.dhis.store.screen.AppDetailsScreen
import com.dhis.store.screen.AppListScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDetailsTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    val appListScreen = AppListScreen()
    val appDetailsScreen = AppDetailsScreen()

    @Test
    fun when_click_in_an_app_item_show_app_details() {
        appListScreen {

            // wait for the loading
            idle(2000)

            navigateToFirstItem()
        }

        appDetailsScreen {
            titleView.isVisible()
        }
    }
}