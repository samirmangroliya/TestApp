package com.samir.testapptexis.data.model

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: Boolean = false,
    val msg: String? = null
)