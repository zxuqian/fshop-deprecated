package com.zxuqian

import com.mongodb.MongoClient
import com.mongodb.MongoClientOptions
import com.zxuqian.data.Category
import io.ktor.util.generateCertificate
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
import io.ktor.gson.GsonConverter
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import org.bson.types.ObjectId
import java.io.File
import java.text.DateFormat

// Register POJO codec
val pojoCodecRegistry:CodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()))


// Initalize mongo connections
val mongoClient = MongoClient(DATA_BASE_HOST, MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build())

fun Application.main() {

    install(DefaultHeaders)
    install(CallLogging)

    install(CORS) {
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        host("localhost:8000")
        allowCredentials = true

    }
    install(ContentNegotiation) {
        gson {
            //register(ContentType.Application.Json, GsonConverter())
            registerTypeAdapter(ObjectId::class.java, ObjectIdSerializer())
            setDateFormat("yyyy-MM-dd HH:mm:ss")
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