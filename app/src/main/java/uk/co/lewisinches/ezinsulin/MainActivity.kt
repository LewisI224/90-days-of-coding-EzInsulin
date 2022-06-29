package uk.co.lewisinches.ezinsulin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.ceil
import kotlin.math.floor


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonListener()

    }

    private fun buttonListener() {

        val calculateButton = findViewById<Button>(R.id.Calculate_Button)

        calculateButton.setOnClickListener {

            //retrieve values from inputs
            val InsulinRatio = findViewById<EditText>(R.id.IR_Number_Input)
            val Carbs = findViewById<EditText>(R.id.CHO_Number_Input)
            val CurrBS = findViewById<EditText>(R.id.currBS)
            val TarBS = findViewById<EditText>(R.id.tarBS)
            val CF = findViewById<EditText>(R.id.CF)

            //convert inputs from string to doubles
            val carbsNumber = Carbs.text.toString().toDouble()
            val insulinRatioNumber = InsulinRatio.text.toString().toDouble()
            val CurrBSNumber = CurrBS.text.toString().toDouble()
            val TarBSNumber = TarBS.text.toString().toDouble()
            val CFNumber = CF.text.toString().toDouble()

            //pass values to dose calculator
            val dose = calculateDose(carbsNumber, insulinRatioNumber, CurrBSNumber, TarBSNumber, CFNumber)
            //set ui output to returned dose values
            val doseText = findViewById<TextView>(R.id.Units_label)
            doseText.text = ("$dose units")
        }
    }


    private fun calculateDose(CHO: Double, IR: Double, CurrBS: Double, TarBS: Double, CF: Double): String {
        var carbDose = CHO/IR
        carbDose = floor(carbDose * 2) /2 .toDouble()
        var corrDose = (CurrBS - TarBS)/ CF
        corrDose = floor(corrDose * 2) /2 .toDouble()
        val totalDose = carbDose + corrDose

        return totalDose.toString()
    }

}