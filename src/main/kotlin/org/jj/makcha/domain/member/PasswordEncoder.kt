package org.jj.makcha.domain.member

import java.security.MessageDigest
import java.util.Base64

private const val ALGORITHM = "SHA-256"

class PasswordEncoder {

    private val digest = MessageDigest.getInstance(ALGORITHM)

    fun encode(password: String): String {
        return encodeOnce(encodeOnce(password))
    }

    private fun encodeOnce(password: String): String {
        return digest.digest(password.toByteArray())
            .let {
                Base64.getEncoder().encodeToString(it)
            }
    }
}
