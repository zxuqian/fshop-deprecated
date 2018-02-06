package com.zxuqian

import com.mongodb.async.client.MongoClient
import com.mongodb.async.client.MongoClients
import com.zxuqian.data.product.ProductData
import com.zxuqian.route.product.product
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.util.generateCertificate
import java.io.File

// Initalize mongo connections
val mongoClient: MongoClient = MongoClients.create()

fun Application.main() {
    install(CORS) {
        host("localhost:3000")
    }
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        product()

        get("/") {
            call.respondText("Hello World3!")
        }
    }
}

class CertificateGenerator {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val file = File("build/temporary.jks")

            if(!file.exists()) {
                file.parentFile.mkdirs()
                generateCertificate(file)
               
            }
        }
    }
}