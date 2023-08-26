package com.example.aplicacionmovil.data.entities.movies.popular

import com.example.aplicacionmovil.logic.data.PopularMovies

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun Result.getPopularMovie(): PopularMovies {
    return PopularMovies(
        id,
        title,
        overview,
        "https://image.tmdb.org/t/p/w500$poster_path",
        original_language,
        release_date,
        vote_average
    )
}