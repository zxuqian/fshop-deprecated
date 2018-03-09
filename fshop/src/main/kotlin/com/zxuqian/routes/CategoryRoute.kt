package com.zxuqian.routes

import com.zxuqian.data.CategoryData
import io.ktor.application.call
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.logging.Level.FINE


fun Route.category() {

    route("/api") {
        route("category") {

            get("/") {
                val logger = LoggerFactory.getLogger("GET /api/category")

                logger.debug("---------------------------------------------------------------")
                logger.debug(CategoryData().findAllCategories().toList().toString())
                call.respond(CategoryData().findAllCategories().toList())
            }

            post("/") {
                val logger = LoggerFactory.getLogger("POST /api/category")
                val text = call.receiveText()

                logger.debug( text + " ====================================")

                CategoryData().createCategory(text)

                call.respondText("Hello World4444!")
                //CategoryData().createCategory()
            }
        }

    }
}