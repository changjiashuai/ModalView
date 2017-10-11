package com.changjiashuai.modalview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Activity
import android.view.View
import android.widget.RelativeLayout
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.changjiashuai.modalview.exts.*


/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/9/30 10:39.
 */
class ModalView(private val activity: Activity) {

    lateinit var contentView: View

    /*弹窗背景颜色*/
    var backgroundResource = R.color.modal_mask

    /*弹窗距离两侧的距离(dp)*/
    var margin: Int = 44
        set(value) {
            field = activity.px2dip(value.toFloat())
        }

    /*当前是否显示*/
    var isShowing = false

    /*显示位置*/
    var position = POSITION_BOTTOM

    /*ModalView视图宽、高*/
    var width = MATCH_PARENT
    var height = WRAP_CONTENT

    fun with(init: ModalView.() -> Unit): ModalView {
        this.init()
        return this
    }

    /**
     * 显示
     *
     * @param anim: default true
     */
    fun show(anim: Boolean = true) {
        if (anim) {
            showWithAnimation()
        } else {
            val lp = RelativeLayout.LayoutParams(width, height)
            lp.addRule(position, RelativeLayout.TRUE)
            if (position == POSITION_CENTER && width == MATCH_PARENT) {
                lp.setMargins(margin, 0, margin, 0)
            }
            activity.addView2DecorView(contentView, backgroundResource = backgroundResource, lp = lp)
            isShowing = true
        }
    }

    /**
     * 消失
     *
     * @param anim: default true
     */
    fun dismiss(anim: Boolean = true) {
        if (anim) {
            dismissWithAnimation()
        } else {
            activity.removeViewFromDecorView(contentView)
            isShowing = false
        }
    }

    /**
     * 显示
     *
     * @param anim:
     */
    fun showWithAnimation(anim: Animation = initAnimIn()) {
        val lp = RelativeLayout.LayoutParams(width, height)
        lp.addRule(position, RelativeLayout.TRUE)
        if (position == POSITION_CENTER && width == MATCH_PARENT) {
            lp.setMargins(margin, 0, margin, 0)
        }
        activity.addView2DecorView(contentView, backgroundResource = backgroundResource, lp = lp)
        isShowing = true
        contentView.startAnimation(anim)
    }

    /**
     * 消失
     *
     * @param anim:
     */
    fun dismissWithAnimation(anim: Animation = initAnimOut()) {
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                activity.removeViewFromDecorView(contentView)
                isShowing = false
            }

            override fun onAnimationStart(p0: Animation?) {
            }
        })
        contentView.startAnimation(anim)
    }

    private fun initAnimIn(): Animation = when (position) {
        POSITION_TOP -> AnimationUtils.loadAnimation(activity, R.anim.slide_in_top)
        POSITION_CENTER -> AnimationUtils.loadAnimation(activity, R.anim.scale_in)
        POSITION_BOTTOM -> AnimationUtils.loadAnimation(activity, R.anim.slide_in_bottom)
        else -> AnimationUtils.loadAnimation(activity, android.R.anim.fade_in)
    }

    private fun initAnimOut(): Animation = when (position) {
        POSITION_TOP -> AnimationUtils.loadAnimation(activity, R.anim.slide_out_top)
        POSITION_CENTER -> AnimationUtils.loadAnimation(activity, R.anim.scale_out)
        POSITION_BOTTOM -> AnimationUtils.loadAnimation(activity, R.anim.slide_out_bottom)
        else -> AnimationUtils.loadAnimation(activity, android.R.anim.fade_out)
    }

    /**
     *  显示
     *
     *  @param animator: 显示时的动画，注意传入的数值
     */
    fun showWithAnimator(animator: ObjectAnimator = ObjectAnimator()) {
        val lp = RelativeLayout.LayoutParams(width, height)
        lp.addRule(position, RelativeLayout.TRUE)
        if (position == POSITION_CENTER && width == MATCH_PARENT) {
            lp.setMargins(margin, 0, margin, 0)
        }
        activity.addView2DecorView(contentView, backgroundResource = backgroundResource, lp = lp)
        isShowing = true
        //fixme maybe have one better method to deal before addView getHeight=0 's issue???
        contentView.post {
            //must be in here getHeight
            if (animator.propertyName == null) {
                initAnimatorIn(animator)
            }
            animator.start()
        }
    }

    /**
     *  消失
     *
     *  @param animator: 消失时的动画，注意传入的数值
     */
    fun dismissWithAnimator(animator: Animator = initAnimatorOut()) {
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                contentView.translationY = 0f
                activity.removeViewFromDecorView(contentView)
                isShowing = false
            }
        })
        animator.start()
    }

    private fun initAnimatorOut(): Animator = when (position) {
        POSITION_TOP -> ObjectAnimator.ofFloat(contentView, "translationY", 0f, -contentView.height.toFloat())
        POSITION_CENTER -> ObjectAnimator.ofFloat(contentView, "translationX", 0f, contentView.width.toFloat())
        POSITION_BOTTOM -> ObjectAnimator.ofFloat(contentView, "translationY", 0f, contentView.height.toFloat())
        else -> ObjectAnimator.ofFloat(contentView, "translationY", 0f, 0f)
    }

    private fun initAnimatorIn(objectAnimator: ObjectAnimator) {
        objectAnimator.target = contentView
        when (position) {
            POSITION_TOP -> {
                objectAnimator.propertyName = "translationY"
                objectAnimator.setFloatValues(-contentView.height.toFloat(), 0f)
            }
            POSITION_CENTER -> {
                objectAnimator.propertyName = "translationX"
                objectAnimator.setFloatValues(contentView.width.toFloat(), 0f)
            }
            POSITION_BOTTOM -> {
                objectAnimator.propertyName = "translationY"
                objectAnimator.setFloatValues(contentView.height.toFloat(), 0f)
            }
        }
    }

    companion object {
        val POSITION_TOP = RelativeLayout.ALIGN_PARENT_TOP
        val POSITION_CENTER = RelativeLayout.CENTER_IN_PARENT
        val POSITION_BOTTOM = RelativeLayout.ALIGN_PARENT_BOTTOM

        val MATCH_PARENT = RelativeLayout.LayoutParams.MATCH_PARENT
        val WRAP_CONTENT = RelativeLayout.LayoutParams.WRAP_CONTENT
    }
}