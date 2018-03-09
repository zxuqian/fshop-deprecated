package com.zxuqian.routes

import com.zxuqian.data.ProductData
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.product() {

    val productData = ProductData()

    route("/api") {
        route("product") {
            get("/") {
                //productData.findAllProducts()

                call.respondText("This is product page")
            }
        }
    }

}