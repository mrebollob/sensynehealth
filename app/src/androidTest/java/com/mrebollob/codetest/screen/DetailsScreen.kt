package com.mrebollob.codetest.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.mrebollob.codetest.R

class DetailsScreen : Screen<DetailsScreen>() {

    val titleView: KTextView = KTextView { withId(R.id.title_view) }
}