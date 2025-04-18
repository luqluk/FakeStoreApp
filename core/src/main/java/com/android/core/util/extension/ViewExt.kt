package com.android.core.util.extension

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

/**
 * Handle view visible
 */
fun View.visible() {
    if (visibility != VISIBLE) visibility = VISIBLE
}

/**
 * Handle view invisible
 */
fun View.invisible() {
    if (visibility != INVISIBLE) visibility = INVISIBLE
}

/**
 * Handle view gone
 */
fun View.gone() {
    if (visibility != GONE) visibility = GONE
}
