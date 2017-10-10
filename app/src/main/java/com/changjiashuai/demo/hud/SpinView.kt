package com.changjiashuai.demo.hud

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.ImageView
import com.changjiashuai.demo.R

class SpinView : ImageView, Indeterminate {

    private var mRotateDegrees: Float = 0f
    private var mFrameTime: Int
    private var mNeedToUpdateView: Boolean = false
    private var mUpdateViewRunnable: Runnable

    override var speed: Float = 1f
        set(value) {
            field = 1000 / 12 / value
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        setImageResource(R.drawable.hud_spinner)
        mFrameTime = 1000 / 12
        mUpdateViewRunnable = object : Runnable {
            override fun run() {
                mRotateDegrees += 30
                mRotateDegrees = if (mRotateDegrees < 360) mRotateDegrees else mRotateDegrees - 360
                invalidate()
                if (mNeedToUpdateView) {
                    postDelayed(this, mFrameTime.toLong())
                }
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.rotate(mRotateDegrees, width.toFloat() / 2, height.toFloat() / 2)
        super.onDraw(canvas)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mNeedToUpdateView = true
        post(mUpdateViewRunnable)
    }

    override fun onDetachedFromWindow() {
        mNeedToUpdateView = false
        super.onDetachedFromWindow()
    }
}