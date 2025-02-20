package com.example.app_grupob.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_grupob.R
import com.example.app_grupob.databinding.ActivityLoginBinding
import com.example.app_grupob.pojos.Usuario
import com.example.app_grupob.pojos.UsuarioEntity
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        CoroutineScope(Dispatchers.IO).launch {
            /* CODIGO PARA BORRAR LOS USUARIOS DE ROOM EN CASO DE ERROR
            val usuarios = UsuarioApplication.database.usuarioDao().getUsuario()
            for (user in usuarios) {
                UsuarioApplication.database.usuarioDao().deleteUsuario(user)
            }
             */

            if (UsuarioApplication.database.usuarioDao().getUsuario().isNotEmpty()) {
                if (RetrofitInstance.api.getUsuarioExiste(UsuarioApplication.database.usuarioDao().getUsuario()[UsuarioApplication.database.usuarioDao().getUsuario().size-1].correo)) {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.e("ERROR_APP", "El usuario de Room ha sido eliminado en la base de datos.")
                }
            }
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            comprobarLogin()
        }

        binding.txtChangeToRegister.setOnClickListener {
            val intent = Intent(context, RegistrationActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun comprobarLogin() {
        if (binding.etLoginEmail.text.isNotEmpty() && binding.etLoginPassword.text.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val correo = binding.etLoginEmail.text.toString()
                    val contrasena = binding.etLoginPassword.text.toString()
                    val existe = RetrofitInstance.api.getUsuarioCorrecto(correo, contrasena)

                    if (existe) {
                        val usuario: Usuario = RetrofitInstance.api.getUsuarioCorreo(correo)

                        val usuarioEntity = UsuarioEntity(usuario.id, usuario.nombre, usuario.apellidos, usuario.correo, "", usuario.longitud, usuario.latitud, usuario.premium)
                        UsuarioApplication.database.usuarioDao().addUsuario(usuarioEntity)

                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "Correo o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    Log.e("ERROR", "Error al acceder a la API: ${e.message}")
                }
            }
        } else {
            Toast.makeText(this, "No puedes dejar campos vacios.", Toast.LENGTH_SHORT).show()
        }
    }
}