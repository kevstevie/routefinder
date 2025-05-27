package org.jj.makcha.domain.member

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PasswordTest : StringSpec({
    "비밀번호 암호화" {
        val password = Password("jj")

        password.matches("jj") shouldBe true
    }

    "비밀번호 일치하지 않음" {
        val password = Password("jj")

        password.matches("jjj") shouldBe false
    }
})
