package com.example.app_grupob.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.app_grupob.R
import com.example.app_grupob.adapters.ArticulosAdapter
import com.example.app_grupob.adapters.OwnArticulosAdapter
import com.example.app_grupob.databinding.FragmentHomeBinding
import com.example.app_grupob.databinding.FragmentProductsBinding
import com.example.app_grupob.listeners.OnClickArticuloListener
import com.example.app_grupob.listeners.OnDeleteArticuloListener
import com.example.app_grupob.pojos.Articulo
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsFragment : Fragment(), OnDeleteArticuloListener {
    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)

        cargarArticulos()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        cargarArticulos()
    }

    override fun eliminarArticulo(articulo: Articulo) {
        val dialog = context?.let {
            AlertDialog.Builder(it)
                .setTitle("Eliminar artículo")
                .setMessage("¿Estás seguro de eliminar el artículo?")
                .setPositiveButton("Eliminar") { _, _ ->
                    eliminarArticuloDeLaBaseDeDatos(articulo)
                    Toast.makeText(it, "Artículo eliminado", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .create()
        }

        dialog?.show()
    }

    fun cargarArticulos() {
        val recyclerViewArticulos = binding.rvArticulosPropios
        recyclerViewArticulos.layoutManager = GridLayoutManager(context, 2)

        val listener:OnDeleteArticuloListener = this
        CoroutineScope(Dispatchers.IO).launch {
            var articulos = RetrofitInstance.api.getArticulos()
            var articulosComprables: MutableList<Articulo> = mutableListOf()
            val usuario = UsuarioApplication.database.usuarioDao().getUsuario()

            for (articulo: Articulo in articulos) {
                if (articulo.usuario.id.equals(usuario.get(0).id)) {
                    articulosComprables.add(articulo)
                }
            }

            withContext(Dispatchers.Main) {
                if (articulosComprables.size == 0) {
                    binding.txtProductosNoSubidos.visibility = View.VISIBLE
                    binding.rvArticulosPropios.visibility = View.GONE
                } else {
                    binding.txtProductosNoSubidos.visibility = View.GONE
                    binding.rvArticulosPropios.visibility = View.VISIBLE
                }

                recyclerViewArticulos.adapter = OwnArticulosAdapter(articulosComprables, listener)
            }
        }
    }

    fun eliminarArticuloDeLaBaseDeDatos(articulo: Articulo) {
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitInstance.api.eliminarArticulo(articulo.id.toString())
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ProductsFragment())
            .commit()
    }
}