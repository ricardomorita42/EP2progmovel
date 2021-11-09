package com.example.myfinancialapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myfinancialapp.databinding.FragmentWeatherBinding
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class WeatherFragment : Fragment() {
    companion object {
        const val AppId = "f4862a0bd37c9685edb345d97c1f63d1"
        var city_name = "Sao Paulo"
    }

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater,container,false)

        binding.weatherSendBtn.setOnClickListener {
            city_name = binding.editPlaceName.text.toString()
            if(city_name != "") {
                getCurrentData()
                binding.editPlaceName.text.clear()
                binding.editPlaceName.hideKeyboard()
                binding.invalidateAll()
            }
        }

        val nameObserver = Observer<String> { newWeather ->
            binding.resultText.text = newWeather
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
        var call = service.getCurrentWeatherData(city_name, AppId)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    val stringBuilder = weatherResponse.name+ "\n" +
                            "Temperatura: " + (weatherResponse.main!!.temp - 273) + "\n"
                            "Humidade: " + (weatherResponse.main!!.humidity)

                    binding.resultText.text = stringBuilder
                    //Toast.makeText(activity,stringBuilder,Toast.LENGTH_LONG).show()
                    binding.invalidateAll()
                } else {
                    if (response != null) {
                        Toast.makeText(activity, response.toString(),Toast.LENGTH_LONG).show()
                    }else {
                        Toast.makeText(activity, "failure", Toast.LENGTH_SHORT).show()
                    }
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
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}