package core.util

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.sh.ex.R
import qiu.niorgai.StatusBarCompat

fun Activity.setStatusBarTransparent() {
    StatusBarCompat.translucentStatusBar(this, true)
}

fun Activity.setStatusBarWhite() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//23
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    }
}

fun Activity.setStatusBarColor(resColor: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//23
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, resColor)
    }
}

fun Context.convertDpToPx(dp: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.resources.displayMetrics).toInt()
}

fun Context.convertPxToDp(px: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, this.resources.displayMetrics).toInt()
}

fun Context.getWidth(activity: Activity): Int {
    val display = activity.windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}

private var widthDisplay: Int = 0
fun setWidthDisplay(ww: Int) {
    widthDisplay = ww
}

fun getWidthDisplay(): Int {
    return widthDisplay
}