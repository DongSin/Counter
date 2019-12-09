package com.example.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CounterViewModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(CounterViewModelClass::class.java)
        textViewCounter.text = viewModel.counter.value.toString()

        viewModel.counter.observe(this,
            Observer<Int> {
                if (viewModel.counter.value == 10) endGame()
            }
        )

        imageViewAdd.setOnClickListener {
            viewModel.increment()
            textViewCounter.text = viewModel.counter.value.toString()
        }

        imageViewMinus.setOnClickListener {
            viewModel.decrement()
            textViewCounter.text = viewModel.counter.value.toString()

        }
    }

    override fun onDestroy() {
        Log.d("Main","onDestroy")
        super.onDestroy()
    }



    private fun endGame(){
        Toast.makeText(applicationContext,"Counter == 10", Toast.LENGTH_LONG).show()
    }
}
