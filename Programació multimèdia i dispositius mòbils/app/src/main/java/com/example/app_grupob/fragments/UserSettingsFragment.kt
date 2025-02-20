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
            val usuario = UsuarioApplication.database.usuarioDao().getUsuario()[UsuarioApplication.database.usuarioDao().getUsuario().size-1]

            withContext(Dispatchers.Main) {
                binding.etModifyName.setText(usuario.nombre)
                binding.etModifySurnames.setText(usuario.apellidos)
            }
        }

        binding.btnModify.setOnClickListener {
            comprobarFormulario()
        }

        return binding.root
    }

    fun comprobarFormulario() {
        if (binding.etModifyName.text.toString().isNotBlank() && binding.etModifySurnames.text.toString().isNotBlank() && binding.etModifyActualPassword.text.toString().isNotBlank() && binding.etModifyNewPassword.text.toString().isNotBlank()) {
            val nombre = binding.etModifyName.text.toString()
            val apellidos = binding.etModifySurnames.text.toString()
            val contrasenaActual = binding.etModifyActualPassword.text.toString()
            val contrasenaNueva = binding.etModifyNewPassword.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val usuarioActual = UsuarioApplication.database.usuarioDao().getUsuario()[UsuarioApplication.database.usuarioDao().getUsuario().size-1]
                if (RetrofitInstance.api.getUsuarioCorrecto(usuarioActual.correo, contrasenaActual)) {
                    val usuario = Usuario(usuarioActual.id.toString().toInt(), nombre, apellidos, usuarioActual.correo, contrasenaNueva, usuarioActual.longitud, usuarioActual.latitud, usuarioActual.premium)

                    RetrofitInstance.api.modificarUsuario(usuario.id.toString(), usuario)
                    modificarRoom(usuario)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Perfil actualizado.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "La contraseña actual no es correcta.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
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

            val usuarioEntity = UsuarioEntity(usuario.id, usuario.nombre, usuario.apellidos, usuario.correo, usuario.contrasena, usuario.longitud, usuario.latitud, usuario.premium)
            UsuarioApplication.database.usuarioDao().addUsuario(usuarioEntity)
        }
    }
}