package com.sapala.approveservice

import com.sapala.approveservice.config.SecurityProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(
    SecurityProperties::class
)
class ApproveServiceApplication {
    companion object {
        val log: Logger = LoggerFactory.getLogger(ApproveServiceApplication::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<ApproveServiceApplication>(*args)
    ApproveServiceApplication.log.info("Swagger available at: http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs")
}