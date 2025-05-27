package org.jj.makcha.domain.member

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long? = null,
    val deviceId: String? = null,
    @Embedded
    val loginInfo: LoginInfo,

    @Enumerated(EnumType.STRING)
    var alertStatus: AlertStatus = AlertStatus.ON
) {
    fun matchesPassword(password: String): Boolean {
        return loginInfo.password.matches(password)
    }

    val isAlertOn: Boolean
        get() = alertStatus.isOn()
}

enum class AlertStatus {
    OFF,
    ON,
    ;

    fun isOn() = this == ON
}

