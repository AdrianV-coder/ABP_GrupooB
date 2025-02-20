package com.example.app_grupob.retrofit

import com.example.app_grupob.pojos.Articulo
import com.example.app_grupob.pojos.ArticuloRequest
import com.example.app_grupob.pojos.Categoria
import com.example.app_grupob.pojos.Usuario
import com.example.app_grupob.pojos.Valoracion
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
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

    @PUT("usuarios/{id}")
    suspend fun modificarUsuario(
        @Path("id") id:String,
        @Body usuario: Usuario
    ): Response<Unit>

    @PUT("usuarios/premium/{idUsuario}")
    suspend fun modificarUsuarioPremium(
        @Path("idUsuario") idUsuario:String
    ): Response<Unit>


    @GET("articulos")
    suspend fun getArticulos():List<Articulo>

    @POST("articulos")
    suspend fun insertarArticulo(
        @Body articulo: ArticuloRequest
    ): Response<Unit>

    @DELETE("articulos/{id}")
    suspend fun eliminarArticulo(
        @Path("id") id:String
    ): Response<Unit>

    @Multipart
    @POST("uploads/imagen/{articuloId}")
    fun subirImagen(
        @Path("articuloId") articuloId: Int,
        @Part file: MultipartBody.Part
    ): Call<Map<String, String>>


    @GET("valoraciones/{id}")
    suspend fun getValoraciones(
        @Path("id") id:String
    ): List<Int>

    @POST("valoraciones")
    suspend fun insertarValoracion(
        @Body valoracion: Valoracion
    ): Response<Unit>


    @GET("usuarios/{id}/favoritos/{articuloId}")
    suspend fun isFavorito(
        @Path("id") id:Int,
        @Path("articuloId") articuloId:Int
    ): Boolean

    @GET("usuarios/{id}/favoritos")
    suspend fun getFavoritos(
        @Path("id") id:Int
    ): List<Articulo>

    @DELETE("usuarios/{id}/favoritos/{articuloId}")
    suspend fun eliminarFavorito(
        @Path("id") id:Int,
        @Path("articuloId") articuloId:Int
    ): Response<Unit>

    @POST("usuarios/{id}/favoritos/{articuloId}")
    suspend fun insertarFavorito(
        @Path("id") id:Int,
        @Path("articuloId") articuloId:Int
    ): Response<Unit>


    @GET("categorias")
    suspend fun getCategorias(
    ): List<Categoria>

    @GET("categorias/{idCategoria}")
    suspend fun getArticulosCategoria(
        @Path("idCategoria") id:Int
    ): List<Articulo>


    @POST("odoo/create-partner")
    suspend fun insertarUsuarioOdoo(
        @Query("name") name: String,
        @Query("email") email: String
    ): Response<ResponseBody>
}