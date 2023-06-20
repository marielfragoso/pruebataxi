package edu.itsco.taxi.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.itsco.taxi.data.AuthRepository
import edu.itsco.taxi.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel(){
    val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()
    var logueado:Boolean = false;

    fun loginUser(email:String, password:String)= viewModelScope.launch {
        repository.loginUser(email, password).collect{result ->
            when(result){
                is Resource.Success ->{
                    _signInState.send(SignInState(isSuccess = "Inicio sesion correctamente"))
                    logueado = true;
                }
                is Resource.Loading ->{
                    _signInState.send(SignInState(isLoading = true))

                }
                is Resource.Error ->{
                    _signInState.send(SignInState(isError = result.message))
                }
            }
        }
    }
}