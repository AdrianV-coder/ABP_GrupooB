package com.example.app_grupob.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_grupob.R
import com.example.app_grupob.databinding.ActivityWelcomeBinding
import com.example.app_grupob.pojos.Usuario
import com.example.app_grupob.pojos.UsuarioEntity
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private var loginExpanded = false
    private var registerExpanded = false
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            if (UsuarioApplication.database.usuarioDao().getUsuario().isNotEmpty()) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
        }

        binding.llLogin.setOnClickListener {
            loginExpanded = !loginExpanded
            modificarLogin(loginExpanded)
        }

        binding.llRegister.setOnClickListener {
            registerExpanded = !registerExpanded
            modificarRegistro(registerExpanded)
        }

        binding.btnLogin.setOnClickListener {
            comprobarLogin()
        }

        binding.btnRegister.setOnClickListener {
            comprobarRegistro()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun modificarLogin(extended: Boolean) {
        if (extended) {
            binding.expandableLoginView.visibility = View.VISIBLE
            binding.expandableRegisterView.visibility = View.GONE
            registerExpanded = false
        } else {
            binding.expandableLoginView.visibility = View.GONE
        }
    }

    fun modificarRegistro(extended: Boolean) {
        if (extended) {
            binding.expandableRegisterView.visibility = View.VISIBLE
            binding.expandableLoginView.visibility = View.GONE
            loginExpanded = false
        } else {
            binding.expandableRegisterView.visibility = View.GONE
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
                        val usuario:Usuario = RetrofitInstance.api.getUsuarioCorreo(correo)

                        val usuarioEntity = UsuarioEntity(null, usuario.nombre, usuario.apellidos, usuario.correo, "", usuario.longitud, usuario.latitud)
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

    fun comprobarRegistro() {
        if (binding.etRegisterEmail.text.isNotEmpty() && binding.etRegisterPassword.text.isNotEmpty() && binding.etRegisterConfirmPassword.text.isNotEmpty()) {
            if (binding.etRegisterPassword.text.toString() == binding.etRegisterConfirmPassword.text.toString()) {
                val context = this
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val correo = binding.etRegisterEmail.text.toString()
                        val contrasena = binding.etRegisterPassword.text.toString()
                        val existe = RetrofitInstance.api.getUsuarioExiste(correo)

                        if (!existe) {
                            // Acabar el formulario de Registro
                            val usuario = Usuario("Joaquin", "Tomas Guerra", correo, contrasena, 0.0, 0.0)

                            val usuarioEntity = UsuarioEntity(null, usuario.nombre, usuario.apellidos, usuario.correo, "", usuario.longitud, usuario.latitud)
                            UsuarioApplication.database.usuarioDao().addUsuario(usuarioEntity)

                            RetrofitInstance.api.insertarUsuario(usuario)

                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, "Este correo ya esta en uso.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("ERROR", "Error al acceder a la API: ${e.message}")
                    }
                }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "No puedes dejar campos vacios.", Toast.LENGTH_SHORT).show()
        }
    }
}