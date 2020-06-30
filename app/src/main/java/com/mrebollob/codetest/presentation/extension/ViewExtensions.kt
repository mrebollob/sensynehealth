package com.mrebollob.codetest.presentation.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.visible(visible: Boolean = true) {
    this.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.snack(
    message: String,
    length: Int = Snackbar.LENGTH_LONG,
    actionText: String? = null,
    action: () -> Unit = {}
) {
    val snack = Snackbar.make(this, message, length)
    if (actionText != null) {
        snack.setAction(actionText) {
            action()
        }
    }
    snack.show()
}
