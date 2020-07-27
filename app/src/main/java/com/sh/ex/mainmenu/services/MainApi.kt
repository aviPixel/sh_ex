package com.sh.ex.mainmenu.services

import com.sh.ex.mainmenu.model.ResUpdateModel
import retrofit2.Call
import retrofit2.http.*

interface MainApi {

    @GET("update")
    fun getUpdate(
    ): Call<ResUpdateModel>

}