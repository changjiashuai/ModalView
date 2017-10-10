package com.changjiashuai.demo.hud

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.changjiashuai.modalview.exts.dip2px

class PieView : View, Determinate {

    private var mWhitePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mGreyPaint: Paint
    private var mBound: RectF

    override var max: Int = 100

    override var progress: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        mWhitePaint.style = Paint.Style.FILL_AND_STROKE
        mWhitePaint.strokeWidth = context.dip2px(0.1f).toFloat()
        mWhitePaint.color = Color.WHITE

        mGreyPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mGreyPaint.style = Paint.Style.STROKE
        mGreyPaint.strokeWidth = context.dip2px(2f).toFloat()
        mGreyPaint.color = Color.WHITE

        mBound = RectF()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = context.dip2px(40f)
        setMeasuredDimension(width, width)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val mAngle = progress * 360f / max
        canvas.drawArc(mBound, 270f, mAngle, true, mWhitePaint)
        val padding = context.dip2px(4f)
        canvas.drawCircle(width.toFloat() / 2, height.toFloat() / 2, width.toFloat() / 2 - padding, mGreyPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val padding = context.dip2px(4f).toFloat()
        mBound.set(padding, padding, w - padding, h - padding)
    }
}