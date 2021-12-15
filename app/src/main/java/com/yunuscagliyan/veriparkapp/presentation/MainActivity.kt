package com.yunuscagliyan.veriparkapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yunuscagliyan.veriparkapp.R
import com.yunuscagliyan.veriparkapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}