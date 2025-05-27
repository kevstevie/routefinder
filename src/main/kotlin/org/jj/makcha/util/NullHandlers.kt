package org.jj.makcha.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

inline fun <reified T> T?.toNonNull() = requireNotNull(this) { "NonNull Type 변경에 실패헀습니다." }

inline fun <reified T, ID> CrudRepository<T, ID>.findByIdOrElseThrow(
    id: ID
): T {
    return findByIdOrNull(id) ?: throw IllegalArgumentException("해당 ID를 가진 객체를 찾을 수 없습니다.")
}
