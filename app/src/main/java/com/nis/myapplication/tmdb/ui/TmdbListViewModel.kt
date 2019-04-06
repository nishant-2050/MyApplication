package com.nis.myapplication.tmdb.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nis.myapplication.ResponseData
import com.nis.myapplication.tmdb.api.TmdbDataHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TmdbListViewModel(application: Application): AndroidViewModel(application) {
    private val mCountryListLiveData = MutableLiveData<ResponseData>()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getAllMovies(): MutableLiveData<ResponseData>{
        if(mCountryListLiveData.value == null){
            viewModelScope.launch {
                mCountryListLiveData.postValue(ResponseData.Progress)

                val popularMovieRequest = withContext(Dispatchers.IO) {
                    TmdbDataHandler.getAllMovies()
                }

                mCountryListLiveData.postValue(popularMovieRequest)
            }
        }
        return mCountryListLiveData
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }

}