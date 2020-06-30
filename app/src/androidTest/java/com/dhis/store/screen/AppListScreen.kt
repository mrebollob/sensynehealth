package com.dhis.store.screen

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.dhis.store.R
import org.hamcrest.Matcher

class AppListScreen : Screen<AppListScreen>() {

    val filtersView: KView = KView { withId(R.id.filters_view) }
    val appListView: KRecyclerView = KRecyclerView({
        withId(R.id.app_list)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val contentView: KView = KView(parent) { withId(R.id.card_view) }
    }

    fun navigateToFirstItem() {
        appListView {
            firstChild<AppListScreen.Item> {
                click()
                idle(500)
                click()
            }
        }
    }
}
