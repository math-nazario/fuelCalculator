package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fuelcalculator.databinding.ActivityResultBinding
import java.text.NumberFormat
import java.util.Locale

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val totalPrice = intent.getDoubleExtra("totalPrice", 0.0)
        val totalConsumption = intent.getDoubleExtra("totalConsumption", 0.0)
        val totalDistance = intent.getDoubleExtra("totalDistance", 0.0)
        val total = intent.getDoubleExtra("total", 0.0)

        val formatedPrice = formatCurrency(totalPrice)
        val formatedExpense = formatCurrency(total)
        binding.tvPrice.text = "R$ $formatedPrice"
        binding.tvConsumption.text = "$totalConsumption KM/L"
        binding.tvDistance.text = "$totalDistance KM"
        binding.tvTotalExpense.text = "R$ $formatedExpense"

        binding.btnRecalculate.setOnClickListener {
            val intent = Intent(this, PriceActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun formatCurrency(value: Double): String {
        val numberFormat = NumberFormat.getInstance(Locale("pt", "BR"))
        numberFormat.maximumFractionDigits = 2
        return numberFormat.format(value)
    }
}