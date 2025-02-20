package com.example.app_grupob.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_grupob.R
import com.example.app_grupob.activities.ConfigurationActivity
import com.example.app_grupob.activities.LoginActivity
import com.example.app_grupob.databinding.FragmentMeBinding
import com.example.app_grupob.pojos.Usuario
import com.example.app_grupob.retrofit.RetrofitInstance
import com.example.app_grupob.room.UsuarioApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MeFragment: Fragment() {
    private lateinit var binding: FragmentMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeBinding.inflate(inflater, container, false)

        CoroutineScope(Dispatchers.IO).launch {
            val usuario = UsuarioApplication.database.usuarioDao().getUsuario()[UsuarioApplication.database.usuarioDao().getUsuario().size-1]
            val valoraciones = RetrofitInstance.api.getValoraciones(usuario.id.toString())
            val totalValoraciones = valoraciones.size
            var valorTotalValoraciones:Float = 0f
            val mediaValoraciones:Float

            if (valoraciones.size > 0) {
                for (i in 0..valoraciones.size-1) {
                    valorTotalValoraciones += valoraciones.get(i)
                }

                mediaValoraciones = (valorTotalValoraciones / totalValoraciones)
            } else {
                mediaValoraciones = 0f
            }

            withContext(Dispatchers.Main) {
                binding.txtNombreUsuario.text = "${usuario.nombre} ${usuario.apellidos.substring(0, 1)}."
                binding.txtValoracionUsuario.text = mediaValoraciones.toString().substring(0, 3)
                binding.txtTotalValoraciones.text = "(${totalValoraciones.toString()})"

                if (mediaValoraciones < 4.5f) {
                    binding.imgStar5.setImageResource(R.drawable.ic_star_grey)
                }
                if (mediaValoraciones < 3.5f) {
                    binding.imgStar4.setImageResource(R.drawable.ic_star_grey)
                }
                if (mediaValoraciones < 2.5f) {
                    binding.imgStar3.setImageResource(R.drawable.ic_star_grey)
                }
                if (mediaValoraciones < 1.5f) {
                    binding.imgStar2.setImageResource(R.drawable.ic_star_grey)
                }
                if (mediaValoraciones < 0.5f) {
                    binding.imgStar1.setImageResource(R.drawable.ic_star_grey)
                }
            }
        }

        binding.cardViewUsuario.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, UserSettingsFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.cardViewProductos.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ProductsFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.cardViewPremium.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, PremiumFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.cardViewConfiguracion.setOnClickListener {
            val intent = Intent(context, ConfigurationActivity::class.java)
            startActivity(intent)
        }

        binding.cardViewAyuda.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HelpFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.cardViewCerrarSesion.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val usuarios = UsuarioApplication.database.usuarioDao().getUsuario()
                for (user in usuarios) {
                    UsuarioApplication.database.usuarioDao().deleteUsuario(user)
                }
            }

            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        esPremium()

        return binding.root
    }

    fun esPremium() {
        CoroutineScope(Dispatchers.IO).launch {
            val usuarioActual = UsuarioApplication.database.usuarioDao().getUsuario()[UsuarioApplication.database.usuarioDao().getUsuario().size-1]
            withContext(Dispatchers.Main) {
                val usuarioNuevo:Usuario = RetrofitInstance.api.getUsuarioCorreo(usuarioActual.correo)
                if (usuarioNuevo.premium) {
                    binding.imgOdoo.visibility = View.VISIBLE
                } else {
                    binding.imgOdoo.visibility = View.GONE
                }
            }
        }
    }
}