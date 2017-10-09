package com.changjiashuai.modalview

import android.app.Activity
import android.view.View
import android.widget.RelativeLayout
import com.changjiashuai.modalview.exts.addView2DecorView
import com.changjiashuai.modalview.exts.removeViewFromDecorView
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.changjiashuai.modalview.exts.px2dip


/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/9/30 10:39.
 */
class ModalView(private val activity: Activity) {

    lateinit var contentView: View

    /*弹窗背景颜色*/
    var backgroundResource = R.color.model_mask

    /*弹窗距离两侧的距离(dp)*/
    var margin: Int = 44
        set(value) {
            field = activity.px2dip(value.toFloat())
        }

    /*当前是否显示*/
    var isShowing = false

    /*显示位置*/
    var position = POSITION_BOTTOM
    /*进入动画*/
    var animIn: Animation? = null
    /*离开动画*/
    var animOut: Animation? = null

    /*ModalView视图宽、高*/
    var width = MATCH_PARENT
    var height = WRAP_CONTENT

    fun with(init: ModalView.() -> Unit): ModalView {
        this.init()
        if (animIn == null || animOut == null) {
            when (position) {
                POSITION_TOP -> {
                    animIn = AnimationUtils.loadAnimation(activity, R.anim.slide_in_top)
                    animOut = AnimationUtils.loadAnimation(activity, R.anim.slide_out_top)
                }
                POSITION_CENTER -> {
                    animIn = AnimationUtils.loadAnimation(activity, R.anim.scale_in)
                    animOut = AnimationUtils.loadAnimation(activity, R.anim.scale_out)
                }
                POSITION_BOTTOM -> {
                    animIn = AnimationUtils.loadAnimation(activity, R.anim.slide_in_bottom)
                    animOut = AnimationUtils.loadAnimation(activity, R.anim.slide_out_bottom)
                }
            }
        }
        return this
    }

    fun dismiss() {
        animOut?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                activity.removeViewFromDecorView(contentView)
                isShowing = false
            }

            override fun onAnimationStart(p0: Animation?) {
            }
        })
        contentView.startAnimation(animOut)
    }

    fun show() {
        val lp = RelativeLayout.LayoutParams(width, height)
        lp.addRule(position, RelativeLayout.TRUE)
        if (position == POSITION_CENTER) {
            lp.setMargins(margin, 0, margin, 0)
        }
        activity.addView2DecorView(contentView, backgroundResource = backgroundResource, lp = lp)
        isShowing = true
        contentView.startAnimation(animIn)
    }

    companion object {
        val POSITION_TOP = RelativeLayout.ALIGN_PARENT_TOP
        val POSITION_CENTER = RelativeLayout.CENTER_IN_PARENT
        val POSITION_BOTTOM = RelativeLayout.ALIGN_PARENT_BOTTOM

        val MATCH_PARENT = RelativeLayout.LayoutParams.MATCH_PARENT
        val WRAP_CONTENT = RelativeLayout.LayoutParams.WRAP_CONTENT
    }
}