package edu.itsco.taxi.view

data class SignUpState(
    val isLoading: Boolean= false,
    val isSuccess: String?="",
    val isError: String?=""
)
