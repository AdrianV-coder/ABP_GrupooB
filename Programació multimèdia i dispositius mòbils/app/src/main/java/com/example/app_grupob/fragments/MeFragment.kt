package com.example.app_grupob.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_grupob.R
import com.example.app_grupob.activities.MainActivity
import com.example.app_grupob.activities.WelcomeActivity

class MeFragment(contextoMainActivity: Context) : Fragment() {
    private val contexto = contextoMainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_me, container, false)

        val cardViewUsuario = view.findViewById<CardView>(R.id.cardViewUsuario)
        val cardViewProductos = view.findViewById<CardView>(R.id.cardViewProductos)
        val cardViewPremium = view.findViewById<CardView>(R.id.cardViewPremium)
        val cardViewConfiguracion = view.findViewById<CardView>(R.id.cardViewConfiguracion)
        val cardViewAyuda = view.findViewById<CardView>(R.id.cardViewAyuda)
        val cardViewCerrarSesion = view.findViewById<CardView>(R.id.cardViewCerrarSesion)

        cardViewUsuario.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, UserSettingsFragment())
                .commit()
        }

        cardViewProductos.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ProductsFragment())
                .commit()
        }

        cardViewPremium.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, PremiumFragment())
                .commit()
        }

        cardViewConfiguracion.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ConfigurationFragment())
                .commit()
        }

        cardViewAyuda.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HelpFragment())
                .commit()
        }

        cardViewCerrarSesion.setOnClickListener {
            // Quitar el usuario de Room

            val intent = Intent(contexto, WelcomeActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}