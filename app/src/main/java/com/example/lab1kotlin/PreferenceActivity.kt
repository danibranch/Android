package com.example.lab1kotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import java.security.AccessController.getContext


class PreferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_preference)
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, MySettingsFragment())
            .commit()

        var preference: Preference? = MySettingsFragment().findPreference("darkMode")

        preference?.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference: Preference, any: Any ->
            Toast.makeText(this, "Preference ${preference.toString()} has value ${any.toString()}", Toast.LENGTH_SHORT)
            val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putBoolean("darkMode", any as Boolean)
                commit()
            }
            true;
        }
    }

}
