package com.chlqudco.develop.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlin.math.pow
import kotlin.math.round

class ResultActivity : AppCompatActivity() {

    private val resultTextView: TextView by lazy {
        findViewById(R.id.resultTextView)
    }

    private val hangulResultTextView: TextView by lazy {
        findViewById(R.id.hangulResultTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        Toast.makeText(this,"기록이 저장되었습니다",Toast.LENGTH_SHORT).show()

        val weight = intent.getIntExtra("weight",0)
        val height = intent.getIntExtra("height",0)

        val bmi = weight / (height/100.0).pow(2.0)

        val resultText = when{
            bmi>35.0 -> "고도 비만"
            bmi>30.0 -> "중도 비만"
            bmi>25.0 -> "경도 비만"
            bmi>23.0 -> "과체중"
            bmi>18.5 -> "정상 체중"
            else -> "저체중"
        }

        val w1 = round(18.5 * (height/100.0).pow(2.0)).toInt()
        findViewById<TextView>(R.id.w1).text = "저체중 : 0KG ~ ${w1}KG"

        val w2 = round(23 * (height/100.0).pow(2.0)).toInt()
        findViewById<TextView>(R.id.w2).text = "정상체중 : ${w1}KG ~ ${w2}KG"

        val w3 = round(25 * (height/100.0).pow(2.0)).toInt()
        findViewById<TextView>(R.id.w3).text = "과체중 : ${w2}KG ~ ${w3}KG"

        val w4 = round(25 * (height/100.0).pow(2.0)).toInt()
        findViewById<TextView>(R.id.w4).text = "비만 : ${w3}KG ~ "


        resultTextView.text = bmi.toString()
        hangulResultTextView.text = resultText
    }
}