package com.example.application_test.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application_test.api.PullLinhas
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationModel @Inject constructor(
    private val pullLinhas: PullLinhas
) : ViewModel() {

    private val _authentication = MutableLiveData<Boolean>()
    val authentication: MutableLiveData<Boolean> get() = _authentication

    fun authentication(token: String) {
        viewModelScope.launch {
            val retorno = pullLinhas.authentication(token)
            _authentication.value = retorno
        }
    }
}