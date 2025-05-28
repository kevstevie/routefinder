package org.jj.makcha.config

import org.jj.makcha.domain.station.StationNameTrieRepository
import org.jj.makcha.domain.station.StationRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StationSearchConfig(
    private val stationRepository: StationRepository
) {

    @Bean
    fun stationNameTrieRepository(): StationNameTrieRepository {
        val stationNameTrieRepository = StationNameTrieRepository()
        stationRepository.findAllNames().forEach { stationName ->
            stationNameTrieRepository.insert(stationName)
        }
        return stationNameTrieRepository
    }
}
