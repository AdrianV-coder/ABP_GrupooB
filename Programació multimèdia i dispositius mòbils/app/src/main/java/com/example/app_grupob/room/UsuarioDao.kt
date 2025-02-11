package com.example.app_grupob.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_grupob.pojos.UsuarioEntity

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuarios")
    fun getUsuario() : List<UsuarioEntity>
    @Insert
    fun addUsuario(usuario: UsuarioEntity)
    @Update
    fun updateUsuario(usuario: UsuarioEntity)
    @Delete
    fun deleteUsuario(usuario: UsuarioEntity)
}