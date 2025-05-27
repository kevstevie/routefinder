package org.jj.makcha.presentation

import org.jj.makcha.application.DestinationService
import org.jj.makcha.auth.Auth
import org.jj.makcha.auth.AuthInfo
import org.jj.makcha.auth.AuthParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DestinationController(
    private val destinationService: DestinationService
) {

    @Auth
    @GetMapping("/destinations")
    fun getDestinations(
        @AuthParam authInfo: AuthInfo
    ): ResponseEntity<List<DestinationResponse>> {
        val destinations = destinationService.getDestinations(authInfo.id)
        return ResponseEntity.ok(
            destinations.map {
                DestinationResponse(
                    id = it.id,
                    name = it.name,
                    line = it.line
                )
            }
        )
    }

    @Auth
    @PostMapping("/destinations")
    fun postDestination(
        @AuthParam authInfo: AuthInfo,
        @RequestBody request: DestinationRequest
    ): ResponseEntity<Unit> {
        destinationService.addDestination(
            stationId = request.stationId,
            memberId = authInfo.id
        )
        return ResponseEntity.ok().build()
    }
}

data class DestinationRequest(
    val stationId: Long
)

data class DestinationResponse(
    val id: Long,
    val name: String,
    val line: String
)
