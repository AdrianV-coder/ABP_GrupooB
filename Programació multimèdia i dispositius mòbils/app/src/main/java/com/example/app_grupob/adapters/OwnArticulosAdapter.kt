package com.example.app_grupob.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_grupob.R
import com.example.app_grupob.databinding.ItemArticuloBinding
import com.example.app_grupob.databinding.ItemOwnArticuloBinding
import com.example.app_grupob.listeners.OnClickArticuloListener
import com.example.app_grupob.listeners.OnDeleteArticuloListener
import com.example.app_grupob.pojos.Articulo
import com.example.app_grupob.retrofit.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OwnArticulosAdapter(private val articulos:List<Articulo>, private val listener: OnDeleteArticuloListener) : RecyclerView.Adapter<OwnArticulosAdapter.ViewHolder>() {

    private val articulosReversed = articulos.reversed()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemOwnArticuloBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_own_articulo , parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = articulosReversed.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var articulo:Articulo = articulosReversed[position]

        with(holder) {
            binding.tvTituloArticulo.text = articulo.titulo
            binding.tvPrecioArticulo.text = String.format(articulo.precio.toString() + "€")
            Picasso.get().load("http://4.211.191.132:8080/App_Api/uploads/" + articulo.id.toString()).into(binding.imgArticulo)

            binding.btnEliminarArticulo.setOnClickListener {
                listener.eliminarArticulo(articulo)
            }
        }
    }
}