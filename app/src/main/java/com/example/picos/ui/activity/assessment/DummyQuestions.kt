package com.example.picos.ui.activity.assessment





//Question for FillQuestionFrag
data class FillQuestion(
    val idFill: Int,
    val questionFill: String,
    val descFill: String,
    val idEditText: Int
)


//Question for CategoricalQuestionFrag
data class CategoricalQuestion(
    val idCat: Int,
    val questionCat: String,
    val descCat: String,
    val optiOneCat: String,
    val optiTwoCat: String,
    val optiThreeCat: String,
    val optiFourCat: String,
    val optiFiveCat: String,
    val optiSixCat: String,
    val optiSevenCat: String,
    val optiEightCat: String,
)

//Question for YNQuestionFrag
data class YnQuestion(
    val idYn: Int,
    val questionYn: String,
    val descYn: String,
    val optiOneYn: String,
    val optiTwoYn: String
)

//Question for AgeCategoryFrag
data class AgeQuestion(
    val idAge: Int,
    val questionAge: String,
    val descAge: String,
    val optiOneAge: String,
    val optiTwoAge: String,
    val optiThreeAge: String
)

//Question for QuestionFrag
data class DummyQuestions(
    val id: Int,
    val question: String,
    val descMarry: String,
    val optiOne: String,
    val optiTwo: String,
    val optiThree: String,
    val optiFour: String
)
