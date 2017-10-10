package com.changjiashuai.demo.hud

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

import com.changjiashuai.modalview.exts.dip2px

/**
 * TODO: document your custom view class.
 */
class BarView : View, Determinate {


    private var mOuterPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mInnerPaint: Paint
    private var mBound: RectF
    private var mInBound: RectF
    private var mBoundGap: Float

    override var max: Int = 100

    override var progress: Int = 0
        set(value) {
            field = value
            mInBound.set(mBoundGap, mBoundGap, (width - mBoundGap) * progress / max, height - mBoundGap)
            invalidate()
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        mOuterPaint.style = Paint.Style.STROKE
        mOuterPaint.strokeWidth = context.dip2px(2f).toFloat()
        mOuterPaint.color = Color.WHITE

        mInnerPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mInnerPaint.style = Paint.Style.FILL
        mInnerPaint.color = Color.WHITE

        mBoundGap = context.dip2px(5f).toFloat()
        mInBound = RectF(mBoundGap, mBoundGap, (width - mBoundGap) * progress / max, height - mBoundGap)
        mBound = RectF()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = context.dip2px(100f)
        val height = context.dip2px(20f)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRoundRect(mBound, mBound.height() / 2, mBound.height() / 2, mOuterPaint)
        canvas.drawRoundRect(mInBound, mInBound.height() / 2, mInBound.height() / 2, mInnerPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val padding = context.dip2px(2f).toFloat()
        mBound.set(padding, padding, w - padding, h - padding)
    }
}