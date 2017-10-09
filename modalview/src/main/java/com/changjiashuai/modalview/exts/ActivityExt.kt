package com.changjiashuai.modalview.exts

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import com.changjiashuai.modalview.R

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/9/30 14:19.
 */

fun Activity.addView2DecorView(view: View, backgroundResource: Int = R.color.model_mask,
                               lp: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)) {
    val decorView: ViewGroup = window.decorView as ViewGroup
    val contentParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    val relativeLayout = RelativeLayout(this)
    relativeLayout.isClickable = true
    relativeLayout.setBackgroundResource(backgroundResource)
    relativeLayout.addView(view, lp)
    decorView.addView(relativeLayout, contentParams)
}

fun Activity.addView2WindowContent(view: View, backgroundResource: Int = R.color.model_mask,
                                   lp: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)) {
    val windowContent = window.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT)
    val contentParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    val relativeLayout = RelativeLayout(this)
    relativeLayout.isClickable = true
    relativeLayout.setBackgroundResource(backgroundResource)
    relativeLayout.addView(view, lp)
    windowContent.addView(relativeLayout, contentParams)
}

fun Activity.removeViewFromWindowContent(view: View) {
    val windowContent = window.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT)
    val parent: ViewGroup = view.parent as ViewGroup
    parent.removeAllViews()
    windowContent.removeView(parent)
}

fun Activity.addView2DecorView(layoutId: Int) {
    addView2DecorView(getViewFromResId(layoutId))
}

fun Activity.addView2WindowContent(layoutId: Int) {
    addView2WindowContent(getViewFromResId(layoutId))
}


fun Activity.removeViewFromWindowContent(layoutId: Int) {
    removeViewFromWindowContent(getViewFromResId(layoutId))
}

fun Activity.removeViewFromDecorView(view: View) {
    val decorView: ViewGroup = window.decorView as ViewGroup
    val parent: ViewGroup = view.parent as ViewGroup
    parent.removeAllViews()
    decorView.removeView(parent)
}

fun Activity.removeViewFromDecorView(layoutId: Int) {
    removeViewFromDecorView(getViewFromResId(layoutId))
}