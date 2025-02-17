package com.example.app_grupob.pojos

data class Valoracion(
    val descripcion:String,
    val estrellas:Int,
    val usuarioValora:Usuario,
    val usuarioValorat:Usuario,
)