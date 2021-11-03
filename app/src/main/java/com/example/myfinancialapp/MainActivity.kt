package com.example.myfinancialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickLeft: Button = findViewById(R.id.left_button)
        val clickCenter: Button = findViewById(R.id.center_button)
        val clickRight: Button = findViewById(R.id.right_button)

        clickLeft.setOnClickListener {
            clickedOnButton(clickLeft)
        }
        clickCenter.setOnClickListener {
            clickedOnButton(clickCenter)
        }
        clickRight.setOnClickListener {
            clickedOnButton(clickRight)
        }
    }

    private fun clickedOnButton(button: Button) {
        val textBox: TextView = findViewById(R.id.important_text)
        textBox.text = "Você clicou em " + button.text

    }


}