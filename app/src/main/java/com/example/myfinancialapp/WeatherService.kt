package com.example.myfinancialapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


//current weather
//api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

//One call API:
//api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}

interface WeatherService {
    @GET("data/2.5/weather?units=metric")
    fun getCurrentWeatherData(@Query("q") city_name: String, @Query("APPID") app_id: String): Call<WeatherResponse>
    //@GET("data/2.5/weather?")
    //fun getCurrentWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") app_id: String): Call<WeatherResponse>
}
