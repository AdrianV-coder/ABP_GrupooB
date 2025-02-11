package com.example.app_grupob.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_grupob.R
import com.example.app_grupob.adapters.ArticulosAdapter
import com.example.app_grupob.databinding.FragmentHomeBinding
import com.example.app_grupob.pojos.Articulo
import com.example.app_grupob.retrofit.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var articulosAdapter: ArticulosAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val recyclerViewArticulos = binding.rvArticulosHome
        recyclerViewArticulos.layoutManager = LinearLayoutManager(context)

        articulosAdapter = ArticulosAdapter(getArticulos(), getTotalArticulos())
        recyclerViewArticulos.adapter = articulosAdapter

        return binding.root
    }

    private fun getArticulos():List<Articulo> {
        var articulos:List<Articulo> = mutableListOf()

        CoroutineScope(Dispatchers.IO).launch {
            articulos = RetrofitInstance.api.getArticulos()
        }

        return articulos
    }

    private fun getTotalArticulos():Int {
        return getArticulos().size
    }
}