package com.changjiashuai.modelview

import android.view.View

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/9/30 10:39.
 */
class ModelView {

    val contentView: View

    constructor(contentView: View) {
        this.contentView = contentView
    }

    var backgroundColor = R.color.background_material_light
}