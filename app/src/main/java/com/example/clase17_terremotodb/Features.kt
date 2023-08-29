package com.example.clase17_terremotodb

data class Features (
    val features: List<Feature>
)

data class Feature (
    val properties: Properties,
    val geometry: Geometry,
    val id: String
)

data class Geometry (
    val coordinates: List<Double>
)
{
    val longitude : Double
        get() = coordinates[0]
    val latitude : Double
        get() = coordinates[1]
}

data class Properties (
    val mag: Double,
    val place: String,
    val time: Long,
)
