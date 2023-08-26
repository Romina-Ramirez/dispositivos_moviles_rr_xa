package com.example.aplicacionmovil.ui.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionmovil.logic.data.NowPlayingMovies
import com.example.aplicacionmovil.logic.moviesLogic.NowMoviesLogic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProgressViewModel : ViewModel() {

    val items = MutableLiveData<List<NowPlayingMovies>>()
    val progressState = MutableLiveData<Int>()

    fun processBackground(time: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val state = View.VISIBLE
            progressState.postValue(state)
            delay(time)
            val state1 = View.GONE
            progressState.postValue(state1)
        }
    }

    fun sumInBackground() {
        val state = View.VISIBLE
        progressState.postValue(state)

        var total = 0
        for (i in 1..300000) {
            total += i
        }

        val state1 = View.GONE
        progressState.postValue(state1)
    }

    suspend fun getNowMovies() {
        progressState.postValue(View.VISIBLE)
        val newItems = NowMoviesLogic().getAllNowMovies()
        items.postValue(newItems)
        progressState.postValue(View.GONE)
    }

}