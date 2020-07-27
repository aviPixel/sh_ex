package com.sh.ex.mainmenu.model

import com.google.gson.annotations.SerializedName

data class ResUpdateModel(
    @SerializedName("success") var success: Boolean = false
)