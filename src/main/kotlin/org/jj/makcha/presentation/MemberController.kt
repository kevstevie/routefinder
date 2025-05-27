package org.jj.makcha.presentation

import jakarta.servlet.http.HttpSession
import org.jj.makcha.application.MemberService
import org.jj.makcha.auth.AUTH_INFO
import org.jj.makcha.auth.Auth
import org.jj.makcha.auth.AuthInfo
import org.jj.makcha.auth.AuthParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService,
) {

    @PostMapping("/members")
    fun join(
        @RequestBody request: JoinRequest
    ): ResponseEntity<Unit> {
        memberService.join(request)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
        session: HttpSession
    ): ResponseEntity<LoginResponse> {
        val id = memberService.login(
            loginId = request.loginId,
            password = request.password
        )
        session.setAttribute(AUTH_INFO, AuthInfo(id))
        return ResponseEntity.ok(LoginResponse(id))
    }

    @Auth
    @PatchMapping("/alert")
    fun updateAlertStatus(
        @RequestBody request: AlertUpdateRequest,
        @AuthParam authInfo: AuthInfo
    ): ResponseEntity<Unit> {
        memberService.updateAlertStatus(request.turnOn, authInfo.id)
        return ResponseEntity.ok().build()
    }
}

data class LoginRequest(
    val loginId: String,
    val password: String
)

data class JoinRequest(
    val deviceId: String,
    val loginId: String,
    val password: String
)

data class LoginResponse(
    val id: Long
)

data class AlertUpdateRequest(
    val turnOn: Boolean
)
