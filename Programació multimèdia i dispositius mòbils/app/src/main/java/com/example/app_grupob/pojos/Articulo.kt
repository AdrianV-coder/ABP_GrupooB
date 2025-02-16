package com.example.app_grupob.pojos

data class Articulo(
    val id:Int,
    val titulo:String,
    val descripcion:String,
    val precio:Double,
    val fechaCreacion:String,
    val usuario:Usuario
)

data class ArticuloRequest(
    val titulo:String,
    val descripcion:String,
    val fechaCreacion:String,
    val precio:Double,
    val usuario:Usuario
    //val foto:Foto
)