package com.example.picos.data

import com.google.gson.annotations.SerializedName

data class PredictionRequest(
    @SerializedName("BMI")
    val bmi: String,
    @SerializedName("Blood Group")
    val bloodGroup: String,
    @SerializedName("Cycle(R/I)")
    val cycleRI: String,
    @SerializedName("Pregnant(Y/N)")
    val pregnantYN: String,
    @SerializedName("No. of aborptions")
    val aborptions: String,
    @SerializedName("Waist:Hip Ratio")
    val waistHipRatio: String,
    @SerializedName("Weight gain(Y/N)")
    val weightGain: String,
    @SerializedName("hair growth(Y/N)")
    val hairGrowth: String,
    @SerializedName("Skin darkening(Y/N)")
    val skinDarkening: String,
    @SerializedName("Hair loss(Y/N)")
    val hairLoss: String,
    @SerializedName("Pimples(Y/N)")
    val pimples: String,
    @SerializedName("Fast food (Y/N)")
    val fastFood: String,
    @SerializedName("Reg.Exercise(Y/N)")
    val regExercise: String,
    @SerializedName("age_category")
    val ageCategory: String,
    @SerializedName("marriage_category")
    val marriageCategory: String
)
