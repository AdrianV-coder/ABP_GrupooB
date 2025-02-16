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
import com.example.app_grupob.databinding.FragmentDisplayArticuloBinding
import com.example.app_grupob.databinding.FragmentUserSettingsBinding
import com.example.app_grupob.pojos.ArticuloRequest
import com.example.app_grupob.pojos.Usuario
import com.example.app_grupob.pojos.UsuarioEntity
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar

class UserSettingsFragment : Fragment() {
    private lateinit var binding: FragmentUserSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserSettingsBinding.inflate(inflater, container, false)

        CoroutineScope(Dispatchers.IO).launch {
            val usuario = UsuarioApplication.database.usuarioDao().getUsuario()[0]

            withContext(Dispatchers.Main) {
                binding.etNombre.setText(usuario.nombre)
                binding.etApellidos.setText(usuario.apellidos)
                binding.etContrasena.setText(usuario.contrasena)

                Log.e("AAAA", "Latitud: ${usuario.latitud.toString()}")
                Log.e("AAAA", "Longitud: ${usuario.longitud.toString()}")
            }
        }

        binding.btnActualizarPerfil.setOnClickListener {
            comprobarFormulario()
        }

        return binding.root
    }

    fun comprobarFormulario() {
        if (binding.etNombre.text.toString().isNotBlank() && binding.etApellidos.text.toString().isNotBlank() && binding.etContrasena.text.toString().isNotBlank()) {
            val nombre = binding.etNombre.text.toString()
            val apellidos = binding.etApellidos.text.toString()
            val contrasena = binding.etContrasena.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val usuarioActual = UsuarioApplication.database.usuarioDao().getUsuario()[0]
                val usuario = Usuario(usuarioActual.id.toString().toInt(), nombre, apellidos, usuarioActual.correo, contrasena, usuarioActual.longitud, usuarioActual.latitud)

                RetrofitInstance.api.modificarUsuario(usuario.id.toString(), usuario)
                modificarRoom(usuario)
            }

            Toast.makeText(context, "Perfil actualizado.", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, UserSettingsFragment())
                .commit()
        } else {
            Toast.makeText(context, "Debes de rellenar todos los campos.", Toast.LENGTH_SHORT).show()
        }
    }

    fun modificarRoom(usuario:Usuario) {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarios = UsuarioApplication.database.usuarioDao().getUsuario()
            for (user in usuarios) {
                UsuarioApplication.database.usuarioDao().deleteUsuario(user)
            }

            val usuarioEntity = UsuarioEntity(usuario.id, usuario.nombre, usuario.apellidos, usuario.correo, usuario.contrasena, usuario.longitud, usuario.latitud)
            UsuarioApplication.database.usuarioDao().addUsuario(usuarioEntity)
        }
    }
}