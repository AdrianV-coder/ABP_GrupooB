package com.example.app_grupob.pojos

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Usuario(
    val id: Int,
    val nombre:String,
    val apellidos:String,
    val correo:String,
    val contrasena:String,
    val longitud:Double,
    val latitud:Double
)

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    val nombre:String,
    val apellidos:String,
    val correo:String,
    val contrasena:String,
    val longitud:Double,
    val latitud:Double
)