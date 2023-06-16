package com.example.picos.data

import com.google.gson.annotations.SerializedName

data class PredictionResponse (
    @SerializedName("predictions")
    val predictions : List<String>
)
