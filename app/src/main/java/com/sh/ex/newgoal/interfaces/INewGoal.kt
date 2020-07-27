package com.sh.ex.newgoal.interfaces

interface INewGoal {

    interface View {
        fun initView()
    }

    interface Navigation {
    }

    interface Api {
        fun apiListGoal()
    }

}