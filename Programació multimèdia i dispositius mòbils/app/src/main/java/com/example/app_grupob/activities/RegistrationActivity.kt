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
import com.example.app_grupob.databinding.ActivityRegistrationBinding
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

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private val context = this
    private var latitud:Double = 0.0
    private var longitud:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            comprobarRegistro()
        }

        binding.txtChangeToLogin.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
                            val usuarioCreado = Usuario(-1, nombre, apellidos, correo, contrasena, longitud, latitud, false)

                            RetrofitInstance.api.insertarUsuario(usuarioCreado)

                            val usuarioNuevo: Usuario = RetrofitInstance.api.getUsuarioCorreo(usuarioCreado.correo)

                            val usuarioEntity = UsuarioEntity(usuarioNuevo.id, usuarioNuevo.nombre, usuarioNuevo.apellidos, usuarioNuevo.correo, usuarioCreado.contrasena, usuarioNuevo.longitud, usuarioNuevo.latitud, usuarioNuevo.premium)
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