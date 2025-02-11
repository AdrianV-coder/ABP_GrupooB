package com.example.app_grupob.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_grupob.R
import com.example.app_grupob.databinding.FragmentDisplayArticuloBinding
import com.example.app_grupob.databinding.FragmentHomeBinding
import com.example.app_grupob.pojos.Articulo

class DisplayArticuloFragment(articulo:Articulo) : Fragment() {
    private lateinit var binding: FragmentDisplayArticuloBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplayArticuloBinding.inflate(inflater, container, false)

        return binding.root
    }
}