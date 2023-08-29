package com.example.clase17_terremotodb

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(value = "significant_week.geojson")
    suspend fun getSignificantQuakesByWeek() : Response<Features>

    @GET(value = "significant_month.geojson")
    suspend fun getSignificantQuakesByMonth() : Response<Features>

    @GET(value = "all_week.geojson")
    suspend fun getAllQuakesByWeek() : Response<Features>
}