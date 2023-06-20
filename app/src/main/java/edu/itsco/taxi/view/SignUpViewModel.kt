package edu.itsco.taxi.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.itsco.taxi.data.AuthRepository
import edu.itsco.taxi.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel(){
    val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()
    var registro:Boolean = false;

    fun registerUser(email:String, password:String)= viewModelScope.launch {
        repository.registerUser(email, password).collect{result ->
            when(result){
                is Resource.Success ->{
                    _signUpState.send(SignUpState(isSuccess = "Registro correcto"))
                    registro = true;
                }
                is Resource.Loading ->{
                    _signUpState.send(SignUpState(isLoading = true))

                }
                is Resource.Error ->{
                    _signUpState.send(SignUpState(isError = result.message))
                }
            }
        }
    }
}