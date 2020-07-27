package com.sh.ex.mainmenu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sh.ex.mainmenu.interfaces.IMain

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val act: IMain.View) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(act) as T
    }

}