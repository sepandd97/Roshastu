package com.roshastudio.roshastu.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.interfaces.Event

class FragmentHomeVm(val app: Application): AndroidViewModel(app) {
    private val _navigateScreen = MutableLiveData<Event<Any>>()
    val navigateScreen: LiveData<Event<Any>> = _navigateScreen
    fun clicitem(){
        _navigateScreen.value= Event(R.id.action_fragmentHome_to_productsDetailFragment)
    }
}