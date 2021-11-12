package com.example.myfinancialapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfinancialapp.databinding.FragmentWeatherBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherFragment : Fragment() {
    companion object {
        const val AppId = "f4862a0bd37c9685edb345d97c1f63d1"
        var city_name = "Sao Paulo"
    }

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val data = ArrayList<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater,container,false)

        val recyclerview = binding.recyclerView
        recyclerview.layoutManager = LinearLayoutManager(activity)

        val adapter = WeatherAdapter(data)
        recyclerview.adapter = adapter

        binding.weatherSendBtn.setOnClickListener {
            city_name = binding.editPlaceName.text.toString()
            if(city_name != "") {
                getCurrentData()
                binding.editPlaceName.text.clear()
                binding.editPlaceName.hideKeyboard()

                binding.invalidateAll()
                recyclerview.adapter!!.notifyDataSetChanged()
            }
        }



        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(city_name, AppId)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    //val stringBuilder = weatherResponse.name+ "\n" +
                    //        "Temperatura: " + (weatherResponse.main!!.temp - 273) + "\n"
                    //        "Humidade: " + (weatherResponse.main!!.humidity)

                    data.add(WeatherViewModel(weatherResponse.name!!,
                    weatherResponse.main!!.temp, weatherResponse.main!!.humidity))

                    //binding.resultText.text = stringBuilder
                    //Toast.makeText(activity,stringBuilder,Toast.LENGTH_LONG).show()
                    binding.invalidateAll()
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    Toast.makeText(activity, response.toString(),Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                binding.resultText.text = t.message
                Toast.makeText(activity,t.message,Toast.LENGTH_SHORT).show()
                binding.invalidateAll()
            }
        })
    }

    //https://stackoverflow.com/questions/41790357/close-hide-the-android-soft-keyboard-with-kotlin
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}