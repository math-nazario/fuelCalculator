package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fuelcalculator.databinding.ActivityPriceBinding
import com.google.android.material.snackbar.Snackbar

class PriceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPriceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnNext.setOnClickListener {
            val totalPriceTemp = binding.edtPrice.text
            if (totalPriceTemp?.isEmpty() == true) {
                val messageEmptyField = getString(R.string.message_empty_field)
                Snackbar.make(
                    binding.edtPrice, messageEmptyField, Snackbar.LENGTH_LONG
                ).show()
            } else {
                val totalPrice: Double = totalPriceTemp.toString().toDouble()
                if (totalPrice == 0.0) {
                    val messageZeroField = getString(R.string.message_zero_field)
                    Snackbar.make(
                        binding.edtPrice, messageZeroField, Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    val intent = Intent(this, ConsumptionActivity::class.java)
                    intent.apply {
                        putExtra("totalPrice", totalPrice)
                    }
                    startActivity(intent)
                }
            }
        }
    }
}