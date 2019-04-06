package com.nis.myapplication.countries.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nis.myapplication.ResponseData
import com.nis.myapplication.countries.api.CountryListDataHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryListViewModel(application: Application): AndroidViewModel(application){
    private val mCountryListLiveData = MutableLiveData<ResponseData>()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getCountries(): MutableLiveData<ResponseData> {
        if(mCountryListLiveData.value == null) {
            viewModelScope.launch {
                mCountryListLiveData.postValue(ResponseData.Progress)
                val allCountries = withContext(Dispatchers.IO) {
                    CountryListDataHandler.getCountries(getApplication())
                }
                mCountryListLiveData.postValue(allCountries)
            }
        }
        return mCountryListLiveData
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}