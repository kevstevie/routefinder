package org.jj.makcha.domain.station

import org.springframework.data.jpa.repository.JpaRepository

interface StationRepository : JpaRepository<Station, Long>, StationQueryRepository
