package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fuelcalculator.databinding.ActivityConsumptionBinding
import com.google.android.material.snackbar.Snackbar

class ConsumptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsumptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityConsumptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val totalPrice = intent.getDoubleExtra("totalPrice", 0.0)

        binding.btnNext.setOnClickListener {
            val totalConsumptionTemp = binding.edtConsumption.text
            if (totalConsumptionTemp?.isEmpty() == true) {
                val messageEmptyField = getString(R.string.message_empty_field)
                Snackbar.make(
                    binding.edtConsumption, messageEmptyField, Snackbar.LENGTH_LONG
                ).show()
            } else {
                val totalConsumption: Double = totalConsumptionTemp.toString().toDouble()
                if (totalConsumption == 0.0) {
                    val messageZeroField = getString(R.string.message_zero_field)
                    Snackbar.make(
                        binding.edtConsumption, messageZeroField, Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    val intent = Intent(this, DistanceActivity::class.java)
                    intent.apply {
                        putExtra("totalPrice", totalPrice)
                        putExtra("totalConsumption", totalConsumption)
                    }
                    startActivity(intent)
                }
            }
        }
    }
}