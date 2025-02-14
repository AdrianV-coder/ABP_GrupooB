package com.example.app_grupob.activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_grupob.R
import com.example.app_grupob.databinding.ActivityMainBinding
import com.example.app_grupob.fragments.FavoritesFragment
import com.example.app_grupob.fragments.HomeFragment
import com.example.app_grupob.fragments.MailboxFragment
import com.example.app_grupob.fragments.MeFragment
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var posicion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        aplicarIdiomaGuardado()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_home -> posicion = 0
                R.id.item_favorites -> posicion = 1
                R.id.item_upload -> posicion = 2
                R.id.item_mailbox -> posicion = 3
                R.id.item_me -> posicion = 4
            }

            cambioFragment()
            true
        }

        window.statusBarColor = ContextCompat.getColor(this, R.color.light_grey)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.white)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun cambioFragment() {
        when (posicion) {
            0 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment())
                .addToBackStack(null)
                .commit()
            1 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FavoritesFragment())
                .addToBackStack(null)
                .commit()
            2 -> {
                val intent = Intent(this, UploadArticuloActivity::class.java)
                startActivity(intent)
            }
            3 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MailboxFragment())
                .addToBackStack(null)
                .commit()
            4 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MeFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun aplicarIdiomaGuardado() {
        val prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val idioma = prefs.getString("idioma", "es") ?: "es"
        cambiarIdioma(this, idioma)
    }

    fun cambiarIdioma(context: Context, idioma: String) {
        val locale = Locale(idioma)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        context.resources.updateConfiguration(config, context.resources.displayMetrics)

        val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        prefs.edit().putString("idioma", idioma).apply()
    }
}