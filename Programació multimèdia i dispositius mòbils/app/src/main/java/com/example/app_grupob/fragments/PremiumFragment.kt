package com.example.app_grupob.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.app_grupob.R
import com.example.app_grupob.activities.MainActivity
import com.example.app_grupob.adapters.ArticulosAdapter
import com.example.app_grupob.databinding.FragmentPremiumBinding
import com.example.app_grupob.pojos.Usuario
import com.example.app_grupob.pojos.UsuarioEntity
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PremiumFragment : Fragment() {
    private lateinit var binding: FragmentPremiumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPremiumBinding.inflate(inflater, container, false)

        esPremium()

        binding.btnConseguirPremium.setOnClickListener {
            guardarOdoo()
            modificarPremium()
        }

        return binding.root
    }

    fun esPremium() {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarioActual = UsuarioApplication.database.usuarioDao().getUsuario()[UsuarioApplication.database.usuarioDao().getUsuario().size-1]
            withContext(Dispatchers.Main) {
                val usuarioNuevo:Usuario = RetrofitInstance.api.getUsuarioCorreo(usuarioActual.correo)
                if (usuarioNuevo.premium) {
                    binding.txtPremium.visibility = View.VISIBLE
                    binding.btnConseguirPremium.visibility = View.GONE
                } else {
                    binding.txtPremium.visibility = View.GONE
                    binding.btnConseguirPremium.visibility = View.VISIBLE
                }
            }
        }
    }

    fun guardarOdoo() {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarioActual = UsuarioApplication.database.usuarioDao().getUsuario()[UsuarioApplication.database.usuarioDao().getUsuario().size-1]
            withContext(Dispatchers.Main) {
                val usuarioNuevo:Usuario = RetrofitInstance.api.getUsuarioCorreo(usuarioActual.correo)
                RetrofitInstance.api.insertarUsuarioOdoo(usuarioNuevo.nombre, usuarioNuevo.correo)
            }
        }
    }

    fun modificarPremium() {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarioActual = UsuarioApplication.database.usuarioDao().getUsuario()[UsuarioApplication.database.usuarioDao().getUsuario().size-1]
            withContext(Dispatchers.Main) {
                val usuarioNuevo:Usuario = RetrofitInstance.api.getUsuarioCorreo(usuarioActual.correo)
                RetrofitInstance.api.modificarUsuarioPremium(usuarioNuevo.id.toString())

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, PremiumFragment())
                    .commit()
            }
        }
    }

}