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
import org.json.JSONArray
import java.net.URL

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private var loginExpanded = false
    private var registerExpanded = false
    private val context = this
    private var latitud:Double = 0.0
    private var longitud:Double = 0.0

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

                        val usuarioEntity = UsuarioEntity(usuario.id, usuario.nombre, usuario.apellidos, usuario.correo, "", usuario.longitud, usuario.latitud)
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
        if (binding.etRegisterEmail.text.isNotEmpty() && binding.etRegisterPassword.text.isNotEmpty() && binding.etRegisterConfirmPassword.text.isNotEmpty() && binding.etRegisterName.text.isNotEmpty() && binding.etRegisterSurnames.text.isNotEmpty()  && binding.etRegistarLocation.text.isNotEmpty()) {
            if (binding.etRegisterPassword.text.toString() == binding.etRegisterConfirmPassword.text.toString()) {
                val nombre = binding.etRegisterName.text.toString()
                val apellidos = binding.etRegisterSurnames.text.toString()
                val localidad = binding.etRegistarLocation.text.toString()
                val correo = binding.etRegisterEmail.text.toString()
                val contrasena = binding.etRegisterPassword.text.toString()

                val context = this
                CoroutineScope(Dispatchers.IO).launch {
                    val coordenadas = searchCity(localidad)

                    if (coordenadas != null) {
                        latitud = coordenadas.first
                        longitud = coordenadas.second
                    }

                    try {
                        val existe = RetrofitInstance.api.getUsuarioExiste(correo)

                        if (!existe) {
                            val usuarioCreado = Usuario(-1,nombre, apellidos, correo, contrasena, longitud, latitud)

                            RetrofitInstance.api.insertarUsuario(usuarioCreado)

                            val usuarioNuevo:Usuario = RetrofitInstance.api.getUsuarioCorreo(usuarioCreado.correo)

                            val usuarioEntity = UsuarioEntity(usuarioNuevo.id, usuarioNuevo.nombre, usuarioNuevo.apellidos, usuarioNuevo.correo, usuarioCreado.contrasena, usuarioNuevo.longitud, usuarioNuevo.latitud)
                            UsuarioApplication.database.usuarioDao().addUsuario(usuarioEntity)

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

    private suspend fun searchCity(city: String): Pair<Double, Double>? {
        return try {
            val url = "https://nominatim.openstreetmap.org/search?q=$city&format=json&countrycodes=ES"
            val response = URL(url).readText()
            val jsonArray = JSONArray(response)

            if (jsonArray.length() > 0) {
                val location = jsonArray.getJSONObject(0)
                val lat = location.getDouble("lat")
                val lon = location.getDouble("lon")
                Pair(lat, lon)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}