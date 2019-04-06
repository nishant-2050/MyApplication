package com.nis.myapplication.tmdb.api

import android.util.Log
import com.nis.myapplication.ResponseData
import com.nis.myapplication.tmdb.data.TmdbMovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import java.lang.Exception

object TmdbDataHandler {
    private val baseUrl = "https://api.themoviedb.org/3/"

    interface GetAllMovies{
        @GET("movie/popular")
        fun getPopularMovie(): Deferred<Response<TmdbMovieResponse>>
    }

    suspend fun getAllMovies(): ResponseData {
        val tmdbApi = RetrofitFactory.retrofit(baseUrl).create(GetAllMovies::class.java)
        try {
            val response = tmdbApi.getPopularMovie().await()
            if(response.isSuccessful){
                return ResponseData.Data(response.body())
            }else {
                return ResponseData.Error
            }
        }catch (e:Exception) {
            Log.e("error", e.toString())
            return ResponseData.Error
        }
    }
}