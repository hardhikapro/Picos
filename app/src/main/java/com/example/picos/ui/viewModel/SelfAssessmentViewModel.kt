package com.example.picos.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.picos.data.ApiConfig
import com.example.picos.data.PredictionRequest
import com.example.picos.data.PredictionResponse
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.nio.Buffer


class SelfAssessmentViewModel : ViewModel() {
    var BMI: String? = null
    var WaistHipRatio: String? = null
    var abortion: String? = null
    var bloodGroup: String? = null
    var cycle: String? = null
    var Pregnant: String? = null
    var weightGain: String? = null
    var hairGrowth: String? = null
    var skinDarkening: String? = null
    var hairLoss: String? = null
    var pimples: String? = null
    var fastFood: String? = null
    var regExercise: String? = null
    var ageCategory: String? = null
    var marriageCategory: String? = null

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun predict() {
        val apiService = ApiConfig.getApiService()
        val request = PredictionRequest(
            BMI ?: "tes",
            bloodGroup ?: "tes",
            cycle ?: "tes",
            Pregnant ?: "tes",
            abortion ?: "tes",
            WaistHipRatio ?: "tes",
            weightGain ?: "tes",
            hairGrowth ?: "tes",
            skinDarkening ?: "tes",
            hairLoss ?: "tes",
            pimples ?: "tes",
            fastFood ?: "tes",
            regExercise ?: "tes",
            ageCategory ?: "tes",
            marriageCategory ?: "tes",
        )
        val call = apiService.predict(request)
        _isLoading.value = true


        call.enqueue(object : Callback<PredictionResponse> {

            override fun onResponse(call: Call<PredictionResponse>, response: Response<PredictionResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseData = response.body()
                    if (responseData != null) {
                        _result.value = responseData.predictions[0]
                    }
                    Log.d("hasil response", responseData.toString())
                } else {
                    Log.d("hasil response", "response error")
                }
            }

            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                Log.d("hasil response", "response error")
            }

        })
    }
}
