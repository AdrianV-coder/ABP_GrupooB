package com.example.app_grupob.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_grupob.R
import com.example.app_grupob.databinding.ItemArticuloBinding
import com.example.app_grupob.pojos.Articulo

class ArticulosAdapter(private val articulos:List<Articulo>, private val totalArticulos:Int) : RecyclerView.Adapter<ArticulosAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemArticuloBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_articulo , parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = articulos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val articulo1 = articulos[(position * 2) - 1]

        with(holder) {
            binding.tvTituloArticulo1.text = articulo1.titulo
            binding.tvPrecioArticulo1.text = articulo1.descripcion
            //Picasso.get().load(articulo1.imagen).into(binding.imgArticulo1)

            binding.cardViewArticulo1.setOnClickListener {
                Log.e("PRUEBA", "Titulo del articulo: ${articulo1.titulo}")
            }
        }

        var articulo2:Articulo
        if (position * 2 >= totalArticulos) {
            articulo2 = articulos[(position * 2) - 1]

            with(holder) {
                binding.tvTituloArticulo2.text = articulo2.titulo
                binding.tvPrecioArticulo2.text = articulo2.descripcion
                //Picasso.get().load(articulo2.imagen).into(binding.imgArticulo2)

                binding.cardViewArticulo2.setOnClickListener {
                    Log.e("PRUEBA", "Titulo del articulo: ${articulo2.titulo}")
                }
            }
        }


    }
}