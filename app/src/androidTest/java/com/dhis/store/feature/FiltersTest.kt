package com.dhis.store.feature

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dhis.store.presentation.features.list.MainActivity
import com.dhis.store.screen.AppListScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FiltersTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    val screen = AppListScreen()

    @Test
    fun when_the_app_get_a_config_with_filters_the_filters_are_visible() {
        screen {
            filtersView.isVisible()
        }
    }
}