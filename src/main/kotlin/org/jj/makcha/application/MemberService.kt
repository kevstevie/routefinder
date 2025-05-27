package org.jj.makcha.application

import org.jj.makcha.domain.member.AlertStatus
import org.jj.makcha.domain.member.LoginInfo
import org.jj.makcha.domain.member.Member
import org.jj.makcha.domain.member.MemberRepository
import org.jj.makcha.domain.member.Password
import org.jj.makcha.presentation.JoinRequest
import org.jj.makcha.util.findByIdOrElseThrow
import org.jj.makcha.util.toNonNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun join(request: JoinRequest) {
        val member = Member(
            deviceId = request.deviceId, loginInfo = LoginInfo(
                id = request.loginId,
                password = Password(request.password)
            )
        )

        memberRepository.save(member)
    }

    fun login(loginId: String, password: String): Long {
        val member = memberRepository.findByLoginInfoId(loginId)
            ?: throw IllegalArgumentException("Invalid login ID")

        check(member.matchesPassword(password)) {
            "Invalid password"
        }

        return member.id.toNonNull()
    }

    fun updateAlertStatus(turnOn: Boolean, memberId: Long) {
        val member = memberRepository.findByIdOrElseThrow(memberId)

        member.alertStatus = if (turnOn) {
            AlertStatus.ON
        } else {
            AlertStatus.OFF
        }
    }
}
