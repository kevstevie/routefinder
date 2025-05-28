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

    "prefix로 전부 찾기" {
        val stationNameTrieRepository = StationNameTrieRepository()

        stationNameTrieRepository.insert("서울역")
        stationNameTrieRepository.insert("서울시청역")
        stationNameTrieRepository.insert("서울대입구역")
        stationNameTrieRepository.insert("서을대")
        stationNameTrieRepository.insert("남서울")

        val results = stationNameTrieRepository.searchAllAfter("서울")

        results.size shouldBe 3
        results shouldBe listOf("서울역", "서울시청역", "서울대입구역")
    }
})
