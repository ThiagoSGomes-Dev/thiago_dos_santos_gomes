package com.example.application_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.application_test.R.*
import com.example.application_test.api.PullLinhas
import com.example.application_test.api.RetrofitHelper
import com.example.application_test.databinding.ActivityMainBinding
import com.example.application_test.model.Linha
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Response


class MainActivity : AppCompatActivity() {

    // private lateinit var btnClique : Button
    // private lateinit var binding: ActivityMainBinding
    private val _authentication = MutableLiveData<Boolean>()
    val authentication: MutableLiveData<Boolean> get() = _authentication

    private val authentication by lazy {
        authentication.
    }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView( binding.root )

        /*btnClique = findViewById(id.btn_clique)
        btnClique.setOnClickListener {

        }*/

        binding.btnClique.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                recuperarLinhas()
            }
        }

    }

    private suspend fun authentication(token: String) {
        viewModelScope.launch {
            val retorno = pullLinhas.authentication(token)
            _authentication.value = retorno
        }
    }

    private suspend fun recuperarLinhas() {

        var retorne: retrofit2.Response<Linha>

        try {
            val pullLinhas = retrofit.create(PullLinhas::class.java)
            var retorno = pullLinhas.recuperarLinhas()
        }catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_linha", "erro ao recuperar")
        }

        if(retorno != null) {
            if(retorno.isSuccessful) {
                val linhas = retorno.body()
            }
        }

    }

}   