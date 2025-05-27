package org.jj.makcha.application

import org.jj.makcha.domain.station.Station
import org.jj.makcha.domain.station.StationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class StationService(
    private val stationRepository: StationRepository
) {
    fun getStations(line: String?, query: String?): List<Station> {
        return stationRepository.findByConditions(line, query)
    }
}
