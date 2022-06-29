package uk.co.lewisinches.ezinsulin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonListener()

    }

    private fun buttonListener() {

        val calculateButton = findViewById<Button>(R.id.Calculate_Button)

        calculateButton.setOnClickListener {
            val carbs = findViewById<EditText>(R.id.CHO_Number_Input)
            val insulinRatio = findViewById<EditText>(R.id.IR_Number_Input)
            val carbsNumber = carbs.text.toString().toInt()
            val insulinRatioNumber = insulinRatio.text.toString().toDouble()
            val dose = calculateDose(carbsNumber, insulinRatioNumber)
            val doseText = findViewById<TextView>(R.id.Units_label)
            doseText.text = ("$dose units")
        }
    }


    private fun calculateDose(carbs: Int, insulinRatio: Double): String {
//        dose = (Insulin Ratio / Carbs) + ((Current BS - Target BS)/ Correction Factor)
        return String.format("%.1f", carbs/insulinRatio)


    }

}