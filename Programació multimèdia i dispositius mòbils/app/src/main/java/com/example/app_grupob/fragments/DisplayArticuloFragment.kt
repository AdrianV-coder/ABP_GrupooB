package com.example.app_grupob.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.app_grupob.R
import com.example.app_grupob.adapters.ArticulosAdapter
import com.example.app_grupob.databinding.FragmentDisplayArticuloBinding
import com.example.app_grupob.databinding.FragmentHomeBinding
import com.example.app_grupob.pojos.Articulo
import com.example.app_grupob.pojos.Usuario
import com.example.app_grupob.pojos.Valoracion
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DisplayArticuloFragment(articuloPulsado: Articulo) : Fragment() {
    private lateinit var binding: FragmentDisplayArticuloBinding
    private var articulo = articuloPulsado
    private var favorito = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplayArticuloBinding.inflate(inflater, container, false)

        comprobarFavorito()

        binding.cardViewUsuario.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ViewUsuarioFragment(articulo.usuario))
                .addToBackStack(null)
                .commit()
        }

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
            ponerArticuloFavorito()
        }

        binding.btnChat.setOnClickListener {
            Toast.makeText(context, "No disponible.", Toast.LENGTH_SHORT).show()
        }

        binding.tvTituloArticulo.text = articulo.titulo
        binding.tvPrecioArticulo.text = String.format(articulo.precio.toString() + "€")
        Picasso.get().load("http://4.211.191.132:8080/App_Api/uploads/" + articulo.id.toString()).into(binding.imgArticulo)

        binding.btnComprar.setOnClickListener {
            comprarArticulo(articulo)
        }

        return binding.root
    }

    private fun comprobarFavorito() {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarioRoom = UsuarioApplication.database.usuarioDao().getUsuario()[0]
            val usuarioActual = RetrofitInstance.api.getUsuarioCorreo(usuarioRoom.correo)
            val isFavorito = RetrofitInstance.api.isFavorito(usuarioActual.id, articulo.id)
            withContext(Dispatchers.Main) {
                favorito = isFavorito
                if (favorito) {
                    binding.btnFavoritos.setIconResource(R.drawable.ic_favorite_full_red)
                } else {
                    binding.btnFavoritos.setIconResource(R.drawable.ic_favorite_empty)
                }
            }
        }
    }

    fun comprarArticulo(articulo: Articulo) {
        val dialog = context?.let {
            AlertDialog.Builder(it)
                .setTitle("Comprar artículo")
                .setMessage("¿Desea comprar el artículo '${articulo.titulo}'?")
                .setPositiveButton("Sí") { _, _ ->
                    eliminarArticuloDeLaBaseDeDatos(articulo)
                    valorarUsuario()
                    Toast.makeText(it, "¡Artículo comprado satisfactoriamente!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .create()
        }

        dialog?.show()
    }

    fun valorarUsuario() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialogo_valoracion, null)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.ratingBar)
        val edtDescripcion = dialogView.findViewById<EditText>(R.id.edtDescripcion)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Añadir Valoración")
            .setView(dialogView)
            .setPositiveButton("Enviar") { _, _ ->
                val estrellas = ratingBar.rating
                val descripcion = edtDescripcion.text.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    val usuarioRoom = UsuarioApplication.database.usuarioDao().getUsuario()[0]
                    val usuarioActual = RetrofitInstance.api.getUsuarioCorreo(usuarioRoom.correo)
                    enviarValoracion(descripcion, estrellas.toInt(), usuarioActual, articulo.usuario)
                }

            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
    }

    fun enviarValoracion(descripcion:String, estrellas:Int, usuarioQueValora:Usuario, usuarioValorado:Usuario) {
        val valoracion = Valoracion(descripcion, estrellas, usuarioQueValora, usuarioValorado)

        CoroutineScope(Dispatchers.IO).launch {
            RetrofitInstance.api.insertarValoracion(valoracion)
        }
    }

    fun eliminarArticuloDeLaBaseDeDatos(articulo: Articulo) {
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitInstance.api.eliminarArticulo(articulo.id.toString())
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()
    }

    private fun ponerArticuloFavorito() {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarioRoom = UsuarioApplication.database.usuarioDao().getUsuario()[UsuarioApplication.database.usuarioDao().getUsuario().size-1]
            val usuarioActual = RetrofitInstance.api.getUsuarioCorreo(usuarioRoom.correo)
            if (favorito) {
                RetrofitInstance.api.eliminarFavorito(usuarioActual.id, articulo.id)
                withContext(Dispatchers.Main) {
                    binding.btnFavoritos.setIconResource(R.drawable.ic_favorite_empty)
                }
                favorito = false
            } else {
                RetrofitInstance.api.insertarFavorito(usuarioActual.id, articulo.id)
                withContext(Dispatchers.Main) {
                    binding.btnFavoritos.setIconResource(R.drawable.ic_favorite_full_red)
                }
                favorito = true
            }
        }
    }
}