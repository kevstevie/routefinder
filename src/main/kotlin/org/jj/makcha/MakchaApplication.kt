package org.jj.makcha

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MakchaApplication

fun main(args: Array<String>) {
    runApplication<MakchaApplication>(*args)
}
