package com.example.app_grupob.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_grupob.R
import com.example.app_grupob.activities.MainActivity
import com.example.app_grupob.adapters.ArticulosAdapter
import com.example.app_grupob.databinding.FragmentHomeBinding
import com.example.app_grupob.listeners.OnClickArticuloListener
import com.example.app_grupob.pojos.Articulo
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment(), OnClickArticuloListener {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val recyclerViewArticulos = binding.rvArticulosHome
        recyclerViewArticulos.layoutManager = GridLayoutManager(context, 2)

        val listener:OnClickArticuloListener = this
        CoroutineScope(Dispatchers.IO).launch {
            var articulos = RetrofitInstance.api.getArticulos()
            var articulosComprables: MutableList<Articulo> = mutableListOf()
            val usuario = UsuarioApplication.database.usuarioDao().getUsuario()

            for (articulo:Articulo in articulos) {
                if (!articulo.usuario.id.equals(usuario.get(0).id)) {
                    articulosComprables.add(articulo)
                }
            }

            withContext(Dispatchers.Main) {
                recyclerViewArticulos.adapter = ArticulosAdapter(articulosComprables, listener)
            }
        }

        return binding.root
    }

    override fun mostrarArticulo(articulo: Articulo) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DisplayArticuloFragment(articulo))
            .addToBackStack(null)
            .commit()
    }
}