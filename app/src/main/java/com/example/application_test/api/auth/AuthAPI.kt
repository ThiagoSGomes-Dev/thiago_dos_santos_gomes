package com.example.application_test.api.auth

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.application_test.databinding.LoadingActivityBinding
import com.example.application_test.MainActivity
import com.example.application_test.model.AuthenticationModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthAPI : AppCompatActivity() {

    private lateinit var binding: LoadingActivityBinding
    private val authenticationModel: AuthenticationModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoadingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.VISIBLE

        if (isConectado(this)) {
            authenticationModel.authenticate("e84223f80e36d348faacae383da61b8116ba90ac8717bc76fee695311966fcc8")
            authenticationModel.authenticationState.observe(this) { isAuthenticated ->
                if (isAuthenticated) {
                    binding.btnConectar.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.btnReConectar.visibility = View.INVISIBLE
                    binding.btnConectar.setOnClickListener {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        } else {
            binding.progressBar.visibility = View.GONE
            binding.btnConectar.visibility = View.GONE
            binding.btnReConectar.visibility = View.VISIBLE
            binding.btnReConectar.setOnClickListener {
                recreate()
            }
        }
    }

    private fun isConectado(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}
