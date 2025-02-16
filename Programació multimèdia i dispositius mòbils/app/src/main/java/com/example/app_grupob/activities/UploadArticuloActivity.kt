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
import com.example.app_grupob.databinding.ActivityConfigurationBinding
import com.example.app_grupob.databinding.ActivityUploadArticuloBinding
import com.example.app_grupob.pojos.ArticuloRequest
import com.example.app_grupob.pojos.Usuario
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

class UploadArticuloActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadArticuloBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUploadArticuloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviar.setOnClickListener {
            comprobarFormulario()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun comprobarFormulario() {
        if (binding.etTitulo.text.isNotBlank() && binding.etDescripcion.text.isNotBlank() && binding.etPrecio.text.isNotBlank()) {
            val titulo = binding.etTitulo.text.toString()

            val descripcion = binding.etDescripcion.text.toString()

            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val fechaCreacion = sdf.format(calendar.time)

            val precio = binding.etPrecio.text.toString().toDouble()

            CoroutineScope(Dispatchers.IO).launch {
                val usuarioRoom = UsuarioApplication.database.usuarioDao().getUsuario()[0]
                val usuario = RetrofitInstance.api.getUsuarioCorreo(usuarioRoom.correo)

                val articulo = ArticuloRequest(titulo, descripcion, fechaCreacion, precio, usuario)
                RetrofitInstance.api.insertarArticulo(articulo)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Debes de rellenar todos los campos.", Toast.LENGTH_SHORT).show()
        }
    }
}