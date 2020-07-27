package com.sh.ex.mainmenu.interfaces

import android.widget.ImageView
import androidx.fragment.app.Fragment

interface IMain {

    interface View {
        fun initView()
        fun initObserve()
        fun setIconMenu(icon: ImageView?)
        fun changeFragment(fragment: Fragment, tag: String)
        fun showOneFragment()
        fun showTwoFragment()
        fun showThreeFragment()
        fun showFourFragment()
        fun showNewGoalFragment()

        fun apiUpdateSuccess()
        fun apiUpdateFail(code: String, message: String)
    }

    interface Navigation {
    }

    interface Api {
        fun apiUpdate()
    }

}