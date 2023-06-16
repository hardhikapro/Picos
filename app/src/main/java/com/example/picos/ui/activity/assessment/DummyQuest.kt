package com.example.picos.ui.activity.assessment

import com.example.picos.R

object DummyQuest {
    fun getDummyQuestions(): ArrayList<DummyQuestions> {

        val questionList = ArrayList<DummyQuestions>()

        val q1 = DummyQuestions(
            1, "How long have you been married or in a committed relationship?",
            "The duration of marriage or committed relationships can be relevant in understanding potential fertility challenges or hormonal imbalances.\n",
            "<5 years",
            "5-10 years",
            "11-15 years ",
            ">15 years"
        )

        questionList.add(q1)
        return questionList
    }
}


object FillQuest {
    fun getFillQuestion(): ArrayList<FillQuestion> {

        val fillquestionList = ArrayList<FillQuestion>()

        val fillq1 = FillQuestion(
            1, "What is your waist-to-hip ratio?\n",
            "Waist-to-hip ratio is a measure of body fat distribution and can be an indicator of potential hormonal imbalances. You can calculate the ratio by dividing your waist to your hip size.\n",
            R.id.waistToHip
        )

        val fillq2 = FillQuestion(
            2, "Have you had any abortions in the past?",
            " The number of abortions can be relevant to assess potential impacts on reproductive health and hormonal balance.\n",
            R.id.abortion
        )

        fillquestionList.add(fillq1)
        fillquestionList.add(fillq2)

        return fillquestionList
    }
}


object CategoricalQuest {
    fun getCategoricalQuestion(): ArrayList<CategoricalQuestion> {

        val catquestionList = ArrayList<CategoricalQuestion>()
        val catq1 = CategoricalQuestion(
            1, "What is your blood group?\n",
            " Knowing your blood group is essential for medical purposes and can impact your health in certain situation.",
            "A+",
            "A-",
            "B+",
            "B-",
            "O+",
            "O-",
            "AB+",
            "AB-",
        )

        catquestionList.add(catq1)


        return catquestionList
    }
}


object YnQuest {
    fun getYnQuestion(): ArrayList<YnQuestion> {

        val ynquestionList = ArrayList<YnQuestion>()

        val ynq1 = YnQuestion(
            1, "Are your menstrual cycles regular or irregular?",
            "Menstrual cycle regularity refers to the consistent timing and duration of your periods. Regular periods usually vary between 23-35 days. Use our tracker feature to track your period cycle",
            "Regular",
            "Irregular",
        )

        val ynq2 = YnQuestion(
            2, "Are you currently pregnant or have you been pregnant before?",
            "Pregnancy history is crucial as it can impact fertility, hormonal balance, and overall reproductive health. It helps in understanding potential risks or complications associated with pregnancy and related conditions like PCOS.",
            "Yes",
            "No",
        )

        val ynq3 = YnQuestion(
            3, "Have you experienced significant weight gain recently?\n",
            "It is important to understand if weight gain is occurring and if it may be related to underlying conditions like PCOS, which can impact overall health and fertility.\n",
            "Yes",
            "No",
        )

        val ynq4 = YnQuestion(
            4, "Have you noticed excessive or abnormal hair growth on your body?\n",
            "If you sense any abnormal hair growth on your body parts, such as face, arms and legs, or elsewhere, it's considered ecessive or abnormal hair growth, also known as hirsutism, can be a symptom of hormonal imbalances, including PCOS. ",
            "Yes",
            "No",
        )

        val ynq5 = YnQuestion(
            5, "Have you experienced skin darkening or changes in pigmentation?",
            "Skin darkening or hyperpigmentation can occur due to hormonal changes and imbalances. It is important to monitor any changes in skin coloration, as it may indicate underlying conditions or imbalances that require medical attention.\n",

            "Yes",
            "No",
        )

        val ynq6 = YnQuestion(
            6, "Have you experienced significant hair loss or thinning?\n",
            "Hair loss or thinning can be associated with hormonal imbalances, including those related to PCOS. Understanding changes in hair density and seeking medical guidance can help address potential underlying causes and explore suitable treatments.\n",

            "Yes",
            "No",
        )

        val ynq7 = YnQuestion(
            7, "Do you frequently experience pimples or acne breakouts?\n",
            "Monitoring the frequency and severity of breakouts helps in identifying potential hormonal issues and determining appropriate skincare or medical interventions.\n",

            "Yes",
            "No",
        )

        val ynq8 = YnQuestion(
            8, "Do you consume fast food frequently?\n",
            " Frequent consumption of fast food can have an impact on overall health, including hormonal balance. It is important to assess dietary habits as they can contribute to weight gain, insulin resistance, and other factors associated with PCOS.\n",
            "Yes",
            "No",
        )

        val ynq9 = YnQuestion(
            9, "Do you engage in regular exercise?\n",
            "Regular exercise plays a crucial role in maintaining overall health, managing weight, and promoting hormonal balance. Assessing exercise habits helps in understanding the level of physical activity and its potential impact on reproductive health.\n",

            "Yes",
            "No",
        )

        ynquestionList.add(ynq1)
        ynquestionList.add(ynq2)
        ynquestionList.add(ynq3)
        ynquestionList.add(ynq4)
        ynquestionList.add(ynq5)
        ynquestionList.add(ynq6)
        ynquestionList.add(ynq7)
        ynquestionList.add(ynq8)
        ynquestionList.add(ynq9)

        return ynquestionList
    }
}

object AgeQuest {
    fun getAgeQuestion(): ArrayList<AgeQuestion> {

        val agequestionList = ArrayList<AgeQuestion>()

        val ageq1 = AgeQuestion(
            1, "Which age category do you belong to?",
            "Age category helps in understanding the potential impact of age-related factors on reproductive health and hormonal balance. It provides insights into the specific age group you belong to for better assessment and recommendations.\n",
            "20s",
            "30s",
            "40s",
        )

        agequestionList.add(ageq1)
        return agequestionList
    }
}