package com.changjiashuai.modalview.exts

import android.content.Context
import android.graphics.PixelFormat
import android.view.*

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/9/30 10:58.
 */

fun Context.dip2px(dpValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun Context.px2dip(pxValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (pxValue / scale + 0.5).toInt()
}

fun Context.screenW(): Int {
    return resources.displayMetrics.heightPixels
}

fun Context.screenH(): Int {
    return resources.displayMetrics.widthPixels
}

fun Context.addView2TopLayer(view: View) {
    val lp = WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_TOAST,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            PixelFormat.TRANSLUCENT or WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW)
    val windowManager: WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.addView(view, lp)
}

fun Context.addView2TopLayer(layoutId: Int) {
    val inflate: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflate.inflate(layoutId, null)
    addView2TopLayer(view)
}

fun Context.getViewFromResId(layoutId: Int): View {
    val inflate: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return inflate.inflate(layoutId, null)
}