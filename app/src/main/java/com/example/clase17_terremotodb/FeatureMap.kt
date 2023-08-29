package com.example.clase17_terremotodb

fun Feature.toTerremoto() = Terremoto(
    id,
    properties.place,
    properties.mag.toString(),
    properties.time.toString(),
    geometry.longitude.toString(),
    geometry.latitude.toString()
)