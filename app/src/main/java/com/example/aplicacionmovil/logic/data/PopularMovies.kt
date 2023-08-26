package com.example.aplicacionmovil.logic.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularMovies (
    val id: Int,
    val title: String,
    val description: String,
    val poster: String,
    val language: String,
    val date: String,
    val vote: Double
) : Parcelable