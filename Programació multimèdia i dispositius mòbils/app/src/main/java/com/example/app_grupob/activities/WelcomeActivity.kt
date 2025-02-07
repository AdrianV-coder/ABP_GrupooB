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
import com.example.app_grupob.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private var loginExpanded = false
    private var registerExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        Se deben hacer 2 desplegables (los pondrá Pau), tanto si se registra o se logea, se debe
        hacer una llamada a la API mediante el correo y guardar el usuario en Room para usos futuros.

        Tanto en el caso de registro y login, se debe hacer una comprobacion de si existe el correo,
        haciendo una llamada al endpoint de la API.

        En caso de login, se debe hacer una comprobación de si la contraseña coincide con el correo.

        Si ya existe un usuario en Room, saltarse esta Activity.
        */

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
            val context = this
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val correo = binding.etLoginEmail.text.toString()
                    val contrasena = binding.etLoginPassword.text.toString()
                    val existe = RetrofitInstance.api.getUsuarioCorrecto(correo, contrasena)

                    if (existe) {
                        val usuario:Usuario = RetrofitInstance.api.getUsuarioCorreo(correo)
                        // Guardar usuario en Room
                        // Pau va perfa que no te done palo, fes-ho perfa

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
                            val usuario = Usuario("Paco", "Perez Pecia", correo, contrasena, 0.0, 0.0)
                            // Acabar el formulario de Registro
                            // Guardar el usuario en Room
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