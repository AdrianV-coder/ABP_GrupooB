package com.example.app_grupob.retrofit

import com.example.app_grupob.pojos.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("usuarios")
    suspend fun getUsuarios():List<Usuario>

    @GET("usuarios/comprobarUsuarioCorrecto")
    suspend fun getUsuarioCorrecto(
        @Query("correo") correo:String,
        @Query("contrasena") contrasena:String
    ):Boolean

    @GET("usuarios/comprobarUsuarioExiste")
    suspend fun getUsuarioExiste(
        @Query("correo") correo:String
    ):Boolean

    @GET("usuarios/correo")
    suspend fun getUsuarioCorreo(
        @Query("correo") correo:String
    ):Usuario

    @POST("usuarios")
    suspend fun insertarUsuario(
        @Body usuario: Usuario
    ): Response<Unit>
}