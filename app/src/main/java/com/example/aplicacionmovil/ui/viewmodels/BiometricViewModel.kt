package com.example.aplicacionmovil.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class BiometricViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()

    suspend fun chargingData() {
        isLoading.postValue(true)
        delay(1000)
        isLoading.postValue(false)
    }

}