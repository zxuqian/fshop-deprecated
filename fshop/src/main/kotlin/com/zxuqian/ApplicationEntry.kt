package com.zxuqian

import com.mongodb.MongoClient
import com.mongodb.MongoClientOptions
import com.mongodb.ServerAddress
import com.mongodb.connection.ClusterSettings
import com.zxuqian.data.Category
import com.zxuqian.data.CategoryData
import io.ktor.util.generateCertificate
import com.zxuqian.data.ProductData
import com.zxuqian.routes.category
import com.zxuqian.routes.product
import com.zxuqian.utils.ObjectIdSerializer
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import org.bson.types.ObjectId
import java.io.File
import java.util.logging.Logger

// Register POJO codec
val pojoCodecRegistry:CodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()))


// Initalize mongo connections
val mongoClient = MongoClient(DATA_BASE_HOST, MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build())

fun Application.main() {
    install(CORS) {
        host("localhost:8000")
        allowCredentials = true

    }
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            registerTypeAdapter(ObjectId::class.java, ObjectIdSerializer())
            setPrettyPrinting()
        }
    }
    install(Routing) {
        product()
        category()

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