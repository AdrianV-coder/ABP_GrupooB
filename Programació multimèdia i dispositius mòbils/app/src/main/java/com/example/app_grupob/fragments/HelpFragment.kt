package com.example.app_grupob.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.example.app_grupob.R
import com.example.app_grupob.databinding.FragmentHelpBinding
import com.example.app_grupob.databinding.FragmentMeBinding

class HelpFragment : Fragment() {
    private lateinit var binding: FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelpBinding.inflate(inflater, container, false)

        binding.cardView1.setOnClickListener {
            val cardViewPulsado = binding.expandableView1
            val visible = binding.expandableView1.isVisible
            mostrarPregunta(cardViewPulsado, visible)
        }

        binding.cardView2.setOnClickListener {
            val cardViewPulsado = binding.expandableView2
            val visible = binding.expandableView2.isVisible
            mostrarPregunta(cardViewPulsado, visible)
        }

        binding.cardView3.setOnClickListener {
            val cardViewPulsado = binding.expandableView3
            val visible = binding.expandableView3.isVisible
            mostrarPregunta(cardViewPulsado, visible)
        }

        binding.cardView4.setOnClickListener {
            val cardViewPulsado = binding.expandableView4
            val visible = binding.expandableView4.isVisible
            mostrarPregunta(cardViewPulsado, visible)
        }

        binding.cardView5.setOnClickListener {
            val cardViewPulsado = binding.expandableView5
            val visible = binding.expandableView5.isVisible
            mostrarPregunta(cardViewPulsado, visible)
        }

        return binding.root
    }

    private fun mostrarPregunta(cardView: LinearLayout, visible: Boolean) {
        binding.expandableView1.visibility = View.GONE
        binding.expandableView2.visibility = View.GONE
        binding.expandableView3.visibility = View.GONE
        binding.expandableView4.visibility = View.GONE
        binding.expandableView5.visibility = View.GONE

        if (!visible) {
            cardView.visibility = View.VISIBLE
        }
    }
}