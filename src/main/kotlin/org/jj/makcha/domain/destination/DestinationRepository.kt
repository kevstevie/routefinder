package org.jj.makcha.domain.destination

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DestinationRepository : JpaRepository<Destination, Long> {

    @Query(
        """
        SELECT d.id, s.name, s.line
        FROM Destination d
        JOIN Station s ON d.stationId = s.id
        WHERE d.memberId = :memberId
        """
    )
    fun findAllWithStation(memberId: Long): List<DestinationDto>
}

data class DestinationDto(
    val id: Long,
    val name: String,
    val line: String
)
