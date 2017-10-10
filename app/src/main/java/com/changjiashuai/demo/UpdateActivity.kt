package com.changjiashuai.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.changjiashuai.modalview.ModalView
import com.changjiashuai.modalview.exts.getViewFromResId
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val updateView = getViewFromResId(R.layout.dialog_check_update)
        val btnUpdate = updateView.findViewById<Button>(R.id.btn_update)

        val modalUpdateView = ModalView(this).with {
            contentView = updateView
            position = ModalView.POSITION_CENTER
        }
        
        btnShow.setOnClickListener {
            modalUpdateView.show()
        }

        btnUpdate.setOnClickListener {
            if (modalUpdateView.isShowing) {
                modalUpdateView.dismiss()
            }
        }
    }

}
