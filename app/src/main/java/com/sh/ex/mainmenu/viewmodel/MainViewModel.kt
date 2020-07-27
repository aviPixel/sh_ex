package com.sh.ex.mainmenu.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sh.ex.mainmenu.interfaces.IMain
import com.sh.ex.mainmenu.model.ResUpdateModel
import com.sh.ex.mainmenu.services.MainApi
import core.api.ApiCallback
import core.api.ApiService

class MainViewModel(private val act: IMain.View) : ViewModel() {

    fun apiUpdate() {
        ApiService.initEndPoint().create(MainApi::class.java)
            .getUpdate().enqueue(object : ApiCallback<ResUpdateModel>() {
                override fun success(data: ResUpdateModel) {
                    Log.d("socket_res_data", Gson().toJson(data))
                    act.apiUpdateSuccess()
                }

                override fun fail(code: String, message: String) {
                    act.apiUpdateFail(code, message)
                }
            })
    }

}