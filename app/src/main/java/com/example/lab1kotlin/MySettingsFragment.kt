package com.example.lab1kotlin

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class MySettingsFragment : PreferenceFragmentCompat()  {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        var preference: Preference? = preferenceManager.findPreference("darkMode")

        preference?.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference: Preference, any: Any ->
            Toast.makeText(context, "Preference ${preference.toString()} has value ${any.toString()}", Toast.LENGTH_SHORT);
            val sharedPref = activity?.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
            if (sharedPref != null)
                with (sharedPref.edit()) {
                    putBoolean("darkMode", any as Boolean)
                    commit()
                }
            true;
        }
    }

}
