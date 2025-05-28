package org.jj.makcha.domain.station

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StationNameTrieRepositoryTest : StringSpec({

    "이름 저장" {
        val stationNameTrieRepository = StationNameTrieRepository()

        stationNameTrieRepository.insert("서울역")

        stationNameTrieRepository.search("서울역") shouldBe true
        stationNameTrieRepository.search("서울역2") shouldBe false
        stationNameTrieRepository.search("서울") shouldBe false
    }
})
