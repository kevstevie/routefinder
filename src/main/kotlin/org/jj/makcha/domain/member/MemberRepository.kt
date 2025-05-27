package org.jj.makcha.domain.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {

    fun findByLoginInfoId(loginId: String): Member?
}
