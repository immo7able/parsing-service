package org.example.parsingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ParsingServiceApplication

fun main(args: Array<String>) {
    runApplication<ParsingServiceApplication>(*args)
}
