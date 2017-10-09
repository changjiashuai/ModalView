package com.changjiashuai.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.changjiashuai.modalview.ModalView
import com.changjiashuai.modalview.exts.getViewFromResId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = getViewFromResId(R.layout.dialog_test)
        val button5 = view.findViewById<Button>(R.id.button5)
        val modalViewTop = ModalView(this).with {
            contentView = view
            height = 700
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
            backgroundResource = R.color.colorAccent
        }

        btnShowTop.setOnClickListener { modalViewTop.show() }
        btnShowCenter.setOnClickListener { modalViewCenter.show() }
        btnShowBottom.setOnClickListener { modalViewBottom.show() }
        btnShowChangeBg.setOnClickListener { modalViewChangeBg.show() }

        button5.setOnClickListener {
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
    }
}