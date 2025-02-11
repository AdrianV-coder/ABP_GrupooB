package com.example.app_grupob.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app_grupob.pojos.UsuarioEntity

@Database(entities = [UsuarioEntity::class], version = 2)
abstract class UsuarioDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}