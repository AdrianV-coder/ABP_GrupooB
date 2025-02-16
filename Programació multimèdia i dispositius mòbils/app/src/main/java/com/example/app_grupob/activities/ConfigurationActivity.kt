package com.example.app_grupob.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.app_grupob.R
import com.example.app_grupob.databinding.ActivityConfigurationBinding
import com.example.app_grupob.fragments.MeFragment

class ConfigurationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = SettingsFragment()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, fragment)
                .commit()
        }

        binding.btnChanges.setOnClickListener {
            val settingsFragment = supportFragmentManager.findFragmentById(R.id.settings) as? SettingsFragment
            settingsFragment?.guardarCambios()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        private var idiomaSeleccionado: String? = null

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val idiomaPref = findPreference<ListPreference>("idioma")
            idiomaPref?.setOnPreferenceChangeListener { _, newValue ->
                idiomaSeleccionado = newValue as String
                true
            }
        }

        fun guardarCambios() {
            val prefs = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)
            prefs.edit().putString("idioma", idiomaSeleccionado).apply()

            requireActivity().finish()
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}