package com.example.app_grupob.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        /*
        if (esPremium()) {
            binding.btnConseguirPremium.isEnabled = false
        } else {
            binding.btnConseguirPremium.isEnabled = true
        }
         */

        binding.btnConseguirPremium.setOnClickListener {
            guardarOdoo()
        }

        return binding.root
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