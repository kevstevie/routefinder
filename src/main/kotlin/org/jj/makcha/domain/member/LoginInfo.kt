package org.jj.makcha.domain.member

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded

@Embeddable
class LoginInfo(
    @Column(unique = true, name = "login_id")
    val id: String,
    @Embedded
    val password: Password
)

class Password(
    rawValue: String
) {
    @Column(name = "password")
    val value: String = encoder.encode(rawValue)

    fun matches(input: String) = value == encoder.encode(input)

    companion object {
        private val encoder = PasswordEncoder()
    }
}
