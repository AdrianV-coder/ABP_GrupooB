package com.example.app_grupob.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_grupob.R
import com.example.app_grupob.activities.MainActivity
import com.example.app_grupob.activities.WelcomeActivity
import com.example.app_grupob.databinding.FragmentDisplayArticuloBinding
import com.example.app_grupob.databinding.FragmentMeBinding
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
            val usuario = UsuarioApplication.database.usuarioDao().getUsuario()
            val valoraciones = RetrofitInstance.api.getValoraciones(usuario.get(0).id.toString())
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
                binding.txtNombreUsuario.text = "${usuario.get(0).nombre} ${usuario.get(0).apellidos.substring(0, 1).toUpperCase()}."
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
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ConfigurationFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.cardViewAyuda.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HelpFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.cardViewCerrarSesion.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val usuario = UsuarioApplication.database.usuarioDao().getUsuario()
                for (i in 0..usuario.size) {
                    UsuarioApplication.database.usuarioDao().deleteUsuario(usuario[i])
                }
            }

            val intent = Intent(context, WelcomeActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}