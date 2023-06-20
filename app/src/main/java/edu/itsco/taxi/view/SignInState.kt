package edu.itsco.taxi.view


data class SignInState(
    val isLoading: Boolean= false,
    val isSuccess: String?="",
    val isError: String?=""
)
