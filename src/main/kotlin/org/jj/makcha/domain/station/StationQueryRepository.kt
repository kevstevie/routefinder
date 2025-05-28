package org.jj.makcha.domain.station

interface StationQueryRepository {

    fun findByConditions(
        line: String?,
        query: String?
    ): List<Station>

    fun findAllNames(): Set<String>
}
