package com.sh.ex.menuone.model

import com.google.gson.annotations.SerializedName

data class GoalModel (
    @SerializedName("title") var title: String = "",
    @SerializedName("detail") var detail: String = "",
    @SerializedName("feel") var feel: String = "",
    @SerializedName("dayLeft") var dayLeft: Int = 0,
    @SerializedName("img") var img: String = "",
    @SerializedName("currentPrice") var currentPrice: Int = 0,
    @SerializedName("totalPrice") var totalPrice: Int = 0
)