package com.changjiashuai.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.changjiashuai.modalview.ModalView
import com.changjiashuai.modalview.exts.getViewFromResId
import com.fivehundredpx.android.blur.BlurringView
import kotlinx.android.synthetic.main.activity_blur_view.*

class BlurViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainView = getViewFromResId(R.layout.activity_blur_view)
        setContentView(mainView)

        val blurView = getViewFromResId(R.layout.dialog_blurview)
        val blurringView = blurView.findViewById<BlurringView>(R.id.blurringView)
        blurringView.setBlurredView(mainView)
        val modalView = ModalView(this).with {
            contentView = blurView
            backgroundResource = android.R.color.transparent
        }
        btnShowBlurDialog.setOnClickListener { modalView.show(false) }
        modalView.contentView.setOnClickListener { modalView.dismiss(false) }
    }
}
