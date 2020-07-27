package com.sh.ex.menuone.view

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import core.custom_ui.BundleSaveState


class ViewItemGoalRe : LinearLayout {

    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        isSaveEnabled = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = ((MeasureSpec.getSize(widthMeasureSpec) - 48) / 2.5).toInt()
        val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)

        setMeasuredDimension(width, newHeightMeasureSpec)
    }

}