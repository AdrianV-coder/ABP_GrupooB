package com.example.app_grupob.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.app_grupob.R
import com.example.app_grupob.adapters.ArticulosAdapter
import com.example.app_grupob.databinding.FragmentHomeBinding
import com.example.app_grupob.listeners.OnClickArticuloListener
import com.example.app_grupob.pojos.Articulo
import com.example.app_grupob.pojos.Categoria
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment(), OnClickArticuloListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var searchView: SearchView
    private var listener: OnClickArticuloListener = this

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val recyclerViewArticulos = binding.rvArticulosHome
        recyclerViewArticulos.layoutManager = GridLayoutManager(context, 2)

        searchView = binding.searchBarArticulos
        searchView.clearFocus()

        searchView.setOnClickListener {
            searchView.isIconified = false
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotBlank()) {
                    filtrarArticulosBusqueda(query, listener)
                } else {
                    llenarRecycler(listener)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        searchView.setOnCloseListener {
            llenarRecycler(listener)
            false
        }

        CoroutineScope(Dispatchers.IO).launch {
            val listaCategorias = RetrofitInstance.api.getCategorias()
            var listaNombreCategorias:MutableList<String> = mutableListOf()
            listaNombreCategorias.add("Todas las Categorias")
            for (categoria in listaCategorias) {
                listaNombreCategorias.add(categoria.nombre)
            }

            withContext(Dispatchers.Main) {
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listaNombreCategorias)
                binding.spinnerCategorias.adapter = adapter
            }
        }

        binding.spinnerCategorias.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val categoriaSeleccionada = parentView?.getItemAtPosition(position) as String

                filtrarArticulosCategoria(categoriaSeleccionada, listener)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {

            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (searchView.query.isNullOrBlank()) {
            llenarRecycler(listener)
        } else {
            filtrarArticulosBusqueda(searchView.query.toString(), listener)
        }
    }

    fun filtrarArticulosBusqueda(busqueda:String, listener: OnClickArticuloListener) {
        CoroutineScope(Dispatchers.IO).launch {
            var articulos = RetrofitInstance.api.getArticulos()
            var articulosComprables: MutableList<Articulo> = mutableListOf()
            val usuario = UsuarioApplication.database.usuarioDao().getUsuario()

            for (articulo:Articulo in articulos) {
                if (!articulo.usuario.id.equals(usuario.get(0).id) && articulo.titulo.uppercase().startsWith(busqueda.uppercase())) {
                    articulosComprables.add(articulo)
                }
            }

            if (articulosComprables.size > 0) {
                withContext(Dispatchers.Main) {
                    binding.rvArticulosHome.adapter = ArticulosAdapter(articulosComprables, listener)
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "No se han encontrado artículos relacionados a '${busqueda}'.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun filtrarArticulosCategoria(nombreCategoria:String, listener: OnClickArticuloListener) {
        if (binding.spinnerCategorias.selectedItemPosition != 0) {
            CoroutineScope(Dispatchers.IO).launch {
                var articulos = RetrofitInstance.api.getArticulos()
                var articulosComprables: MutableList<Articulo> = mutableListOf()
                val usuario = UsuarioApplication.database.usuarioDao().getUsuario()

                for (articulo: Articulo in articulos) {
                    val categoriaNombre = articulo.categoria.nombre

                    if (articulo.categoria != null && !articulo.usuario.id.equals(usuario.get(0).id) && categoriaNombre.trim() == nombreCategoria.trim()) {
                        articulosComprables.add(articulo)
                    }
                }

                withContext(Dispatchers.Main) {
                    binding.rvArticulosHome.adapter = ArticulosAdapter(articulosComprables, listener)
                }
            }
        } else {
            llenarRecycler(this)
        }
    }

    fun llenarRecycler(listener: OnClickArticuloListener) {
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
                binding.rvArticulosHome.adapter = ArticulosAdapter(articulosComprables, listener)
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