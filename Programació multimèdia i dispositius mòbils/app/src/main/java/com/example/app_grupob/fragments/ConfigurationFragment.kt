package com.example.app_grupob.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_grupob.R
import com.example.app_grupob.databinding.FragmentConfigurationBinding
import com.example.app_grupob.databinding.FragmentHomeBinding

class ConfigurationFragment : Fragment() {
    private lateinit var binding: FragmentConfigurationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfigurationBinding.inflate(inflater, container, false)



        return binding.root
    }
}