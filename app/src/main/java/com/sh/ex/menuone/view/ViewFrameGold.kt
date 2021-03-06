package com.sh.ex.menuone.view

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import core.custom_ui.BundleSaveState

class ViewItemGoal : CardView {

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

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val savedState = superState?.let { BundleSaveState(it) }
        // savedState.getBundle().putString("key", value);

        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        val bundleSaveState = state as BundleSaveState
        super.onRestoreInstanceState(state)

        val bundle = bundleSaveState.getBundle()
    }

}