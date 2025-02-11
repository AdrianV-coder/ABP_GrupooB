package com.example.app_grupob.pojos

data class Articulo(
    val id:Int,
    val titulo:String,
    val descripcion:String,
    val precio:Double,
    val fechaCreacion:String,
    val usuario:UsuarioArticulo,
    val foto:Foto
)