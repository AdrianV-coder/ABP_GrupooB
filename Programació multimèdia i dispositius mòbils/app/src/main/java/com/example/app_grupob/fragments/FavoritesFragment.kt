package com.example.app_grupob.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.app_grupob.R
import com.example.app_grupob.adapters.ArticulosAdapter
import com.example.app_grupob.adapters.OwnArticulosAdapter
import com.example.app_grupob.databinding.FragmentFavoritesBinding
import com.example.app_grupob.databinding.FragmentHelpBinding
import com.example.app_grupob.listeners.OnClickArticuloListener
import com.example.app_grupob.pojos.Articulo
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesFragment : Fragment(), OnClickArticuloListener {
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        val recyclerViewArticulos = binding.rvArticulosFavoritos
        recyclerViewArticulos.layoutManager = GridLayoutManager(context, 2)

        cargarArticulos(this)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        cargarArticulos(this)
    }

    fun cargarArticulos(listener: OnClickArticuloListener) {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarioRoom = UsuarioApplication.database.usuarioDao().getUsuario()[0]
            val usuarioActual = RetrofitInstance.api.getUsuarioCorreo(usuarioRoom.correo)
            var articulos = RetrofitInstance.api.getFavoritos(usuarioActual.id)

            withContext(Dispatchers.Main) {
                if (articulos.size == 0) {
                    binding.txtNoFavoritos.visibility = View.VISIBLE
                    binding.rvArticulosFavoritos.visibility = View.GONE
                } else {
                    binding.txtNoFavoritos.visibility = View.GONE
                    binding.rvArticulosFavoritos.visibility = View.VISIBLE
                }

                binding.rvArticulosFavoritos.adapter = ArticulosAdapter(articulos, listener)
            }
        }
    }

    override fun mostrarArticulo(articulo: Articulo) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DisplayArticuloFragment(articulo))
            .addToBackStack(null)
            .commit()
    }
}