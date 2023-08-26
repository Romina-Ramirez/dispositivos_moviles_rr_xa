package com.example.aplicacionmovil.data.entities.movies.popular

data class PopMovies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)