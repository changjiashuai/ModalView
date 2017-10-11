package com.changjiashuai.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.changjiashuai.modalview.ModalView
import com.changjiashuai.modalview.exts.getViewFromResId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = getViewFromResId(R.layout.dialog_test)
        val cardView = getViewFromResId(R.layout.dialog_cardview)
        val ivClose = cardView.findViewById<ImageView>(R.id.iv_close)

        val modalViewBasic = ModalView(this).with {
            contentView = view
        }

        val modalViewTop = ModalView(this).with {
            contentView = view
            position = ModalView.POSITION_TOP
        }

        val modalViewCenter = ModalView(this).with {
            contentView = view
            height = 500
            position = ModalView.POSITION_CENTER
        }

        val modalViewBottom = ModalView(this).with {
            contentView = view
            height = ModalView.MATCH_PARENT
            position = ModalView.POSITION_BOTTOM
        }

        val modalViewChangeBg = ModalView(this).with {
            contentView = view
            height = 600
            position = ModalView.POSITION_BOTTOM
            backgroundResource = android.R.color.transparent
        }

        val modalCardView = ModalView(this).with {
            contentView = cardView
            position = ModalView.POSITION_CENTER
        }

        btnShowBasic.setOnClickListener { modalViewBasic.showWithAnimator() }
        btnShowTop.setOnClickListener { modalViewTop.showWithAnimation() }
        btnShowCenter.setOnClickListener { modalViewCenter.show() }
        btnShowBottom.setOnClickListener { modalViewBottom.show() }
        btnShowChangeBg.setOnClickListener { modalViewChangeBg.show() }
        btnShowCardView.setOnClickListener { modalCardView.show() }

        val btnDismiss = view.findViewById<Button>(R.id.btnDismiss)
        btnDismiss.setOnClickListener {
            if (modalViewBasic.isShowing) {
                modalViewBasic.dismissWithAnimator()
            }
            if (modalViewTop.isShowing) {
                modalViewTop.dismiss()
            }
            if (modalViewCenter.isShowing) {
                modalViewCenter.dismiss()
            }
            if (modalViewBottom.isShowing) {
                modalViewBottom.dismiss()
            }
            if (modalViewChangeBg.isShowing) {
                modalViewChangeBg.dismiss()
            }
        }

        ivClose.setOnClickListener {
            if (modalCardView.isShowing) {
                modalCardView.dismiss()
            }
        }

        btnShowLoading.setOnClickListener { startActivity(Intent(this, LoadingActivity::class.java)) }
        btnShowUpdate.setOnClickListener { startActivity(Intent(this, UpdateActivity::class.java)) }
    }
}