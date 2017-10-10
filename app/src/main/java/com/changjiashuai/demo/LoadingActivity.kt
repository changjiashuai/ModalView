package com.changjiashuai.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.changjiashuai.demo.hud.AnnularView
import com.changjiashuai.demo.hud.BarView
import com.changjiashuai.demo.hud.Determinate
import com.changjiashuai.demo.hud.PieView
import com.changjiashuai.modalview.ModalView
import com.changjiashuai.modalview.exts.getViewFromResId
import kotlinx.android.synthetic.main.activity_loading.*
import java.util.*
import kotlin.concurrent.fixedRateTimer

class LoadingActivity : AppCompatActivity() {

    companion object {
        val TAG = "LoadingActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val modalViewWithAnnular = initModalView(R.layout.dialog_loading_annular)
        val annularView = modalViewWithAnnular.contentView.findViewById<AnnularView>(R.id.annularView)
        btnShowAnnularView.setOnClickListener {
            modalViewWithAnnular.show()
            mockProgress(annularView)
        }
        modalViewWithAnnular.contentView.setOnClickListener { modalViewWithAnnular.dismiss() }

        val modalViewWithBar = initModalView(R.layout.dialog_loading_bar)
        val barView = modalViewWithBar.contentView.findViewById<BarView>(R.id.barView)
        btnShowBarView.setOnClickListener {
            modalViewWithBar.show()
            mockProgress(barView)
        }
        modalViewWithBar.contentView.setOnClickListener { modalViewWithBar.dismiss() }

        val modalViewWithPie = initModalView(R.layout.dialog_loading_pie)
        val pieView = modalViewWithPie.contentView.findViewById<PieView>(R.id.pieView)
        btnShowPieView.setOnClickListener {
            modalViewWithPie.show()
            mockProgress(pieView)
        }
        modalViewWithPie.contentView.setOnClickListener { modalViewWithPie.dismiss() }

        val modalViewWithSpin = initModalView(R.layout.dialog_loading_spin)
        btnShowSpinView.setOnClickListener { modalViewWithSpin.show() }
        modalViewWithSpin.contentView.setOnClickListener { modalViewWithSpin.dismiss() }
    }

    var timer: Timer? = null

    private fun mockProgress(determinate: Determinate) {
        var progress = 0
        if (timer != null) {
            timer?.cancel()
        }
        timer = fixedRateTimer(period = 100.toLong()) {
            if (progress == 100) {
                cancel()
                return@fixedRateTimer
            }
            progress += 1
            runOnUiThread {
                determinate.progress = progress
                Log.i(TAG, "progress=$progress")
            }
        }
    }

    private fun initModalView(resId: Int): ModalView {
        val view = getViewFromResId(resId)
        return ModalView(this).with {
            contentView = view
            width = ModalView.WRAP_CONTENT
            position = ModalView.POSITION_CENTER
            backgroundResource = android.R.color.transparent
        }
    }
}
