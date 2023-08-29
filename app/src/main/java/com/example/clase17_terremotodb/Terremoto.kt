package com.example.clase17_terremotodb

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Terremoto(
    val id: String,
    val place: String,
    val magnitude: String,
    val time: String,
    val long: String,
    val lat: String
): Parcelable
