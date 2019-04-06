package com.nis.myapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nis.myapplication.databinding.ActivityCountriesBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
