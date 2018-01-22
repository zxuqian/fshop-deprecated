package com.zxuqian

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Application.main() {
    install(CORS) {
        host("localhost:3000")
    }
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {
            call.respondText("Hello World3!")
        }
    }
}