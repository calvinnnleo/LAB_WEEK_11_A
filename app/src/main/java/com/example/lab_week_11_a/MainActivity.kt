package com.example.lab_week_11_a

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the preference wrapper from the application
        val preferenceWrapper = (application as PreferenceApplication).preferenceWrapper

        // Create the view model instance with the preference wrapper
        val preferenceViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return PreferenceViewModel(preferenceWrapper) as T
            }
        })[PreferenceViewModel::class.java]

        // Observe the text live data
        preferenceViewModel.getText().observe(this) {
            findViewById<TextView>(R.id.activity_main_text_view).text = it
        }

        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            // Save the text when the button is clicked
            val inputText = findViewById<EditText>(R.id.activity_main_edit_text).text.toString()
            preferenceViewModel.saveText(inputText)
        }
    }
}