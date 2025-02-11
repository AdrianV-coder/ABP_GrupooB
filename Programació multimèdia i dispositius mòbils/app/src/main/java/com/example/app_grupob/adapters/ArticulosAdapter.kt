package com.example.app_grupob.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_grupob.R
import com.example.app_grupob.databinding.ItemArticuloBinding
import com.example.app_grupob.listeners.OnClickArticuloListener
import com.example.app_grupob.pojos.Articulo
import com.example.app_grupob.retrofit.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticulosAdapter(private val articulos:List<Articulo>, private val listener: OnClickArticuloListener) : RecyclerView.Adapter<ArticulosAdapter.ViewHolder>() {

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
        var articulo:Articulo = articulos[position]

        with(holder) {
            binding.tvTituloArticulo.text = articulo.titulo
            binding.tvPrecioArticulo.text = String.format(articulo.precio.toString() + "€")
            Picasso.get().load("http://10.249.99.206:8080/uploads/" + articulo.id.toString()).into(binding.imgArticulo)

            binding.cardViewArticulo.setOnClickListener {
                listener.mostrarArticulo(articulo)
            }
        }
    }
}