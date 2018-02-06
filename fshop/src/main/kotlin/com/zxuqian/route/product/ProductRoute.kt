package com.zxuqian.route.product

import com.zxuqian.data.product.ProductData
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.product() {
    val productData = ProductData()
    route("/product") {
        get("/") {
            productData.findAllProducts()

            call.respondText("This is product page")
        }
    }
}