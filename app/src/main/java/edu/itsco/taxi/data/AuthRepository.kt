package edu.itsco.taxi.data

import com.google.firebase.auth.AuthResult
import edu.itsco.taxi.util.Resource
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String) :Flow<Resource<AuthResult>>
}