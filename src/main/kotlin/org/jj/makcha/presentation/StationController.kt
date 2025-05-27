package org.jj.makcha.presentation

import org.jj.makcha.application.StationService
import org.jj.makcha.util.toNonNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class StationController(
    private val stationService: StationService
) {

    @GetMapping("/stations")
    fun list(
        @RequestParam(required = false) line: String? = null,
        @RequestParam(required = false, name = "q") query: String? = null
    ): ResponseEntity<List<StationResponse>> {
        val stations = stationService.getStations(line, query)
        return ResponseEntity.ok(stations.map {
            StationResponse(
                id = it.id.toNonNull(),
                name = it.name,
                line = it.line
            )
        })
    }
}

data class StationResponse(
    val id: Long,
    val name: String,
    val line: String
)
