package com.example.richtext.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.richtext.R
import com.example.richtext.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }

        subscribeUi()
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            viewModel.spannableStringBuilder.collectLatest { spannableStringBuilder ->
                binding.text.text = spannableStringBuilder
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val data = assets.open("data.json").reader().readText()
        viewModel.fetchText(data, baseContext)
    }
}