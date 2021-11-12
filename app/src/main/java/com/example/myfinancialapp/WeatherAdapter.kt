package com.example.myfinancialapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter(private val wList: List<WeatherViewModel>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        val WeatherViewModel = wList[position]

        holder.cityName.text = WeatherViewModel.city_name
        holder.temperature.text = "Temp: " + WeatherViewModel.temperature.toString() + " °C"
        holder.humidity.text = "Humidity: " + WeatherViewModel.humidity.toString() + " %"
    }

    override fun getItemCount(): Int {
        return wList.size
    }

    class ViewHolder(WeatherView: View) : RecyclerView.ViewHolder(WeatherView) {
        val cityName: TextView = WeatherView.findViewById(R.id.cityName)
        val temperature: TextView = WeatherView.findViewById(R.id.temperature)
        val humidity: TextView = WeatherView.findViewById(R.id.humidity)
    }
}