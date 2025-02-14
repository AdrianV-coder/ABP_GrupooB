package com.example.app_grupob.listeners

import com.example.app_grupob.pojos.Articulo

interface OnDeleteArticuloListener {
    fun eliminarArticulo(articulo:Articulo)
}