package com.example.app_grupob.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_grupob.R
import com.example.app_grupob.databinding.ActivityUploadArticuloBinding
import com.example.app_grupob.pojos.ArticuloRequest
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadArticuloActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadArticuloBinding
    private var selectedImageUri: Uri? = null  // Variable para almacenar la imagen seleccionada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUploadArticuloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón para seleccionar imagen
        binding.btnSubirImagen.setOnClickListener {
            seleccionarImagen()
        }

        // Botón para subir el artículo y la imagen
        binding.btnEnviar.setOnClickListener {
            comprobarFormulario { articuloId ->
                if (articuloId > 0) {
                    Toast.makeText(this, "Artículo registrado correctamente", Toast.LENGTH_SHORT).show()

                    // Si hay una imagen seleccionada, subirla
                    selectedImageUri?.let { subirImagen(it, articuloId) }

                    onBackPressedDispatcher.onBackPressed()
                } else {
                    Toast.makeText(this, "Error al registrar el artículo", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Botón para cancelar
        binding.btnCancelar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Método para abrir la galería
    private fun seleccionarImagen() {
        ImagePicker.with(this)
            .galleryOnly()
            .compress(1024)
            .createIntent { intent -> imagePickerLauncher.launch(intent) }
    }

    // Resultado de la selección de imagen
    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            selectedImageUri = data?.data // Guardamos la URI de la imagen
            Toast.makeText(this, "Imagen seleccionada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun subirImagen(imageUri: Uri, articuloId: Int) {
        try {
            val inputStream = contentResolver.openInputStream(imageUri)
            if (inputStream == null) {
                Toast.makeText(this, "Error: No se pudo abrir la imagen", Toast.LENGTH_SHORT).show()
                return
            }

            val tempFile = File(cacheDir, "upload.jpg")
            tempFile.outputStream().use { output -> inputStream.copyTo(output) }

            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), tempFile)
            val body = MultipartBody.Part.createFormData("file", tempFile.name, requestFile)

            RetrofitInstance.api.subirImagen(articuloId, body)
                .enqueue(object : Callback<Map<String, String>> {
                    override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                        if (response.isSuccessful) {
                            val imageUrl = response.body()?.get("imageUrl")
                            Toast.makeText(this@UploadArticuloActivity, "Imagen subida: $imageUrl", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@UploadArticuloActivity, "Error al subir la imagen", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                        Toast.makeText(this@UploadArticuloActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        } catch (e: Exception) {
            Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun comprobarFormulario(callback: (Int) -> Unit) {
        if (binding.etTitulo.text.isNotBlank() && binding.etDescripcion.text.isNotBlank() && binding.etPrecio.text.isNotBlank()) {
            val titulo = binding.etTitulo.text.toString()
            val descripcion = binding.etDescripcion.text.toString()
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val fechaCreacion = sdf.format(calendar.time)
            val precio = binding.etPrecio.text.toString().toDouble()

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val usuarioRoom = UsuarioApplication.database.usuarioDao().getUsuario()[0]
                    val usuario = RetrofitInstance.api.getUsuarioCorreo(usuarioRoom.correo)
                    val articulo = ArticuloRequest(titulo, descripcion, fechaCreacion, precio, usuario)

                    RetrofitInstance.api.insertarArticulo(articulo)

                    buscarArticulo { articuloId ->
                        callback(articuloId) // Retorna el ID del artículo
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@UploadArticuloActivity, "Error al guardar el artículo", Toast.LENGTH_SHORT).show()
                        callback(-1) // Indicar error
                    }
                }
            }
        } else {
            Toast.makeText(this, "Debes rellenar todos los campos.", Toast.LENGTH_SHORT).show()
            callback(-1)
        }
    }

    fun buscarArticulo(callback: (Int) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            var id = -1

            try {
                val usuarioRoom = UsuarioApplication.database.usuarioDao().getUsuario()
                val articulos = RetrofitInstance.api.getArticulos()
                val articulosComprables = articulos.filter { it.usuario.id == usuarioRoom[0].id }

                if (articulosComprables.isNotEmpty()) {
                    id = articulosComprables.last().id
                }
            } catch (e: Exception) {
                Log.e("UploadArticuloActivity", "Error en buscarArticulo: ${e.message}")
            }

            withContext(Dispatchers.Main) {
                callback(id)
            }
        }
    }
}
