package com.example.app_grupob.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.app_grupob.R
import com.example.app_grupob.databinding.FragmentHelpBinding
import com.example.app_grupob.databinding.FragmentPremiumBinding

class PremiumFragment : Fragment() {
    private lateinit var binding: FragmentPremiumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPremiumBinding.inflate(inflater, container, false)

        // Si el usuario es premium, el boton estara desactivado.
        binding.btnConseguirPremium.setOnClickListener {
            // Se debe hacer llamada a Odoo con el Usuario pasado.
            Toast.makeText(context, "Has conseguido el Premium.", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}