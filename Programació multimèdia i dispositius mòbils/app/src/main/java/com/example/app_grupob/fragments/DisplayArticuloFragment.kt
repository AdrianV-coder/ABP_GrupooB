package com.example.app_grupob.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import com.example.app_grupob.R
import com.example.app_grupob.adapters.ArticulosAdapter
import com.example.app_grupob.databinding.FragmentDisplayArticuloBinding
import com.example.app_grupob.databinding.FragmentHomeBinding
import com.example.app_grupob.pojos.Articulo
import com.example.app_grupob.retrofit.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DisplayArticuloFragment(articuloPulsado:Articulo) : Fragment() {
    private lateinit var binding: FragmentDisplayArticuloBinding
    private var articulo = articuloPulsado

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplayArticuloBinding.inflate(inflater, container, false)

        binding.txtNombreUsuario.text = "${articulo.usuario.nombre} ${articulo.usuario.apellidos.substring(0, 1).toUpperCase()}."
        CoroutineScope(Dispatchers.IO).launch {
            val valoraciones = RetrofitInstance.api.getValoraciones(articulo.usuario.id.toString())
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

        binding.btnFavoritos.setOnClickListener {
            //ponerArticuloFavorito()
        }

        binding.btnChat.setOnClickListener {

        }

        binding.tvTituloArticulo.text = articulo.titulo
        binding.tvPrecioArticulo.text = String.format(articulo.precio.toString() + "€")
        Picasso.get().load("http://4.211.191.132/uploads/" + articulo.id.toString()).into(binding.imgArticulo)

        binding.btnComprar.setOnClickListener {

        }

        return binding.root
    }

    /*
    fun ponerArticuloFavorito() {
        if (articulo es favorito) {
            binding.btnFavoritos.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_favorite_empty) }
            Quitar articulo de favoritos
        } else {
            binding.btnFavoritos.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_favorite_full_red) }
            Añadir articulo a favoritos
        }
    }
    */
}