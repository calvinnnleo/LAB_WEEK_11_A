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

        // Get the settings store from the application (DATASTORE)
        val settingsStore = (application as SettingsApplication).settingsStore

        // Create the view model instance with the settings store
        val settingsViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SettingsViewModel(settingsStore) as T
            }
        })[SettingsViewModel::class.java]

        // Observe the text live data
        settingsViewModel.textLiveData.observe(this) {
            findViewById<TextView>(R.id.activity_main_text_view).text = it
        }

        // Save text when button is clicked OR when EditText changes
        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            val inputText = findViewById<EditText>(R.id.activity_main_edit_text).text.toString()
            settingsViewModel.saveText(inputText)
        }
    }
}