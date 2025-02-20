package com.example.app_grupob.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.app_grupob.R
import com.example.app_grupob.adapters.ArticulosAdapter
import com.example.app_grupob.adapters.OwnArticulosAdapter
import com.example.app_grupob.databinding.FragmentDisplayArticuloBinding
import com.example.app_grupob.databinding.FragmentViewUsuarioBinding
import com.example.app_grupob.listeners.OnClickArticuloListener
import com.example.app_grupob.listeners.OnDeleteArticuloListener
import com.example.app_grupob.pojos.Articulo
import com.example.app_grupob.pojos.Usuario
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewUsuarioFragment(usuarioPasado:Usuario) : Fragment(), OnMapReadyCallback, OnClickArticuloListener {
    private lateinit var binding: FragmentViewUsuarioBinding
    private lateinit var mMap: GoogleMap
    private var usuario = usuarioPasado

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewUsuarioBinding.inflate(inflater, container, false)

        CoroutineScope(Dispatchers.IO).launch {
            val valoraciones = RetrofitInstance.api.getValoraciones(usuario.id.toString())
            val totalValoraciones = valoraciones.size
            var valorTotalValoraciones:Float = 0f
            val mediaValoraciones:Float

            if (valoraciones.size > 0) {
                for (i in 0..valoraciones.size-1) {
                    valorTotalValoraciones += valoraciones.get(i)
                }

                mediaValoraciones = (valorTotalValoraciones / totalValoraciones)
            } else {
                mediaValoraciones = 0f
            }

            withContext(Dispatchers.Main) {
                binding.txtNombreUsuario.text = "${usuario.nombre} ${usuario.apellidos.substring(0, 1).toUpperCase()}."
                binding.txtValoracionUsuario.text = mediaValoraciones.toString().substring(0, 3)
                binding.txtTotalValoraciones.text = "(${totalValoraciones.toString()})"

                if (mediaValoraciones < 4.5f) {
                    binding.imgStar5.setImageResource(R.drawable.ic_star_grey)
                }
                if (mediaValoraciones < 3.5f) {
                    binding.imgStar4.setImageResource(R.drawable.ic_star_grey)
                }
                if (mediaValoraciones < 2.5f) {
                    binding.imgStar3.setImageResource(R.drawable.ic_star_grey)
                }
                if (mediaValoraciones < 1.5f) {
                    binding.imgStar2.setImageResource(R.drawable.ic_star_grey)
                }
                if (mediaValoraciones < 0.5f) {
                    binding.imgStar1.setImageResource(R.drawable.ic_star_grey)
                }
            }
        }

        val recyclerViewArticulos = binding.rvArticulosUsuario
        recyclerViewArticulos.layoutManager = GridLayoutManager(context, 2)

        llenarRecycler(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapa) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        updateMap(usuario.latitud, usuario.longitud)
    }

    fun llenarRecycler(listener: OnClickArticuloListener) {
        CoroutineScope(Dispatchers.IO).launch {
            var articulos = RetrofitInstance.api.getArticulos()
            var articulosComprables: MutableList<Articulo> = mutableListOf()

            for (articulo:Articulo in articulos) {
                if (articulo.usuario.id.equals(usuario.id)) {
                    articulosComprables.add(articulo)
                }
            }

            withContext(Dispatchers.Main) {
                binding.rvArticulosUsuario.adapter = ArticulosAdapter(articulosComprables, listener)
            }
        }
    }

    private fun updateMap(lat: Double, lon: Double) {
        val cityLocation = LatLng(lat, lon)
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(cityLocation))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cityLocation, 12f))
    }

    override fun mostrarArticulo(articulo: Articulo) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DisplayArticuloFragment(articulo))
            .addToBackStack(null)
            .commit()
    }
}