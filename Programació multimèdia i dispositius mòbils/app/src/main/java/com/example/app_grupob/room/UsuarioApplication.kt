package com.example.app_grupob.room

import android.app.Application
import androidx.room.Room

class UsuarioApplication : Application() {
    companion object{
        lateinit var database: UsuarioDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext,
            UsuarioDatabase::class.java,
            "UsuarioDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }
}