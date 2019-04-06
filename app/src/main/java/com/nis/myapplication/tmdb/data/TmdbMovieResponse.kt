package com.nis.myapplication.tmdb.data

data class TmdbMovie(
    val id: Int,
    val vote_average: Double,
    val title: String,
    val overview: String,
    val adult: Boolean) {
    val poster_path: String = ""
        get() = "http://image.tmdb.org/t/p/w300$field"
    val backdrop_path: String = ""
        get() = "http://image.tmdb.org/t/p/w300$field"
}

data class TmdbMovieResponse(
    val results: List<TmdbMovie>
)