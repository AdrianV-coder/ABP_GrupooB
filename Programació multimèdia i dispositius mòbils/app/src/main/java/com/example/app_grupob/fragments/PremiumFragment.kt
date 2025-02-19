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
            cambiarPremium()
            guardarOdoo()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, PremiumFragment())
                .commit()
        }

        return binding.root
    }

    fun esPremium() {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarioActual = UsuarioApplication.database.usuarioDao().getUsuario()[0]
            withContext(Dispatchers.Main) {
                if (usuarioActual.premium) {
                    binding.txtPremium.visibility = View.VISIBLE
                    binding.btnConseguirPremium.visibility = View.GONE
                } else {
                    binding.txtPremium.visibility = View.GONE
                    binding.btnConseguirPremium.visibility = View.VISIBLE
                }
            }
        }
    }

    fun cambiarPremium() {
        CoroutineScope(Dispatchers.IO).launch {
            var usuarioActual = UsuarioApplication.database.usuarioDao().getUsuario()[0]
            eliminarUsuariosRoom()
            val usuarioActualPremium = RetrofitInstance.api.getUsuarioCorreo(usuarioActual.correo)
            val usuarioEntityPremium = UsuarioEntity(usuarioActualPremium.id, usuarioActualPremium.nombre, usuarioActualPremium.apellidos, usuarioActualPremium.correo, "", usuarioActualPremium.longitud, usuarioActualPremium.latitud, true)
            UsuarioApplication.database.usuarioDao().addUsuario(usuarioEntityPremium)
        }
    }

    fun eliminarUsuariosRoom() {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarios = UsuarioApplication.database.usuarioDao().getUsuario()
            for (user in usuarios) {
                UsuarioApplication.database.usuarioDao().deleteUsuario(user)
            }
        }
    }

    fun guardarOdoo() {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarioActual = UsuarioApplication.database.usuarioDao().getUsuario()[0]
            val usuarioNuevo:Usuario = RetrofitInstance.api.getUsuarioCorreo(usuarioActual.correo)

            RetrofitInstance.api.insertarUsuarioOdoo(usuarioNuevo.id)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Eres usuario Premium", Toast.LENGTH_SHORT).show()
            }
        }
    }

}