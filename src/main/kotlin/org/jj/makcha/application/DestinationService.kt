package org.jj.makcha.application

import org.jj.makcha.domain.destination.Destination
import org.jj.makcha.domain.destination.DestinationDto
import org.jj.makcha.domain.destination.DestinationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class DestinationService(
    private val destinationRepository: DestinationRepository
) {
    fun addDestination(stationId: Long, memberId: Long) {
        destinationRepository.save(
            Destination(
                stationId = stationId,
                memberId = memberId
            )
        )
    }

    fun getDestinations(memberId: Long): List<DestinationDto> {
        return destinationRepository.findAllWithStation(memberId)
    }
}
