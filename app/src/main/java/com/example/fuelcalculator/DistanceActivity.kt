package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fuelcalculator.databinding.ActivityDistanceBinding
import com.google.android.material.snackbar.Snackbar

class DistanceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDistanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDistanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val totalPrice = intent.getDoubleExtra("totalPrice", 0.0)
        val totalConsumption = intent.getDoubleExtra("totalConsumption", 0.0)

        binding.btnCalculate.setOnClickListener {
            val totalDistanceTemp = binding.edtDistance.text
            if (totalDistanceTemp?.isEmpty() == true) {
                val messageEmptyField = getString(R.string.message_empty_field)
                Snackbar.make(
                    binding.edtDistance, messageEmptyField, Snackbar.LENGTH_LONG
                ).show()
            } else {
                val totalDistance: Double = totalDistanceTemp.toString().toDouble()
                if (totalDistance == 0.0) {
                    val messageZeroField = getString(R.string.message_zero_field)
                    Snackbar.make(
                        binding.edtDistance, messageZeroField, Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    val total = (totalDistance / totalConsumption) * totalPrice
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.apply {
                        putExtra("totalPrice", totalPrice)
                        putExtra("totalConsumption", totalConsumption)
                        putExtra("totalDistance", totalDistance)
                        putExtra("total", total)
                    }
                    startActivity(intent)
                }
            }
        }
    }
}