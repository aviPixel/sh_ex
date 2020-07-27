package com.sh.ex.menuone.interfaces



interface IMenuOne {

    interface View {
        fun initView()

        fun apiListGoalSuccess()
        fun apiListGoalFail()
        fun apiListOfferSuccess()
        fun apiListOfferFail()
        fun apiListSuggestSuccess()
        fun apiListSuggestFail()
    }

    interface Navigation {
        fun goToNewGoal()
    }

    interface Api {
        fun apiListGoal()
        fun apiListOffer()
        fun apiListSuggest()
    }

}