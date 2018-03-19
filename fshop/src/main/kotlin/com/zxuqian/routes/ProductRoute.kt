package com.zxuqian.routes

import com.zxuqian.data.Product
import com.zxuqian.data.ProductData
import com.zxuqian.exceptions.DataException
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import org.bson.types.ObjectId

fun Route.product() {

    val productData = ProductData()

    route("/api") {
        route("product") {
            get("/") {
                //productData.findAllProducts()

                val products = productData.getAll()

                call.respond(HttpStatusCode.OK, products)
            }

            post("/") {

                try {
                    val product = call.receive<Product>()
                    productData.insert(product)

                    call.respond(HttpStatusCode.Created, "{ success: true }")
                } catch (e: DataException) {
                    call.respond(HttpStatusCode.InternalServerError, "{ success: false }")
                }


            }

            put("/{id}") {
                try {
                    val id = call.parameters["id"].let{ ObjectId(it) }
                    val product = call.receive<Product>()
                    product.id = id
                    productData.update(product)

                    call.respond(HttpStatusCode.Created, "{ success: true }")
                } catch (e: DataException) {
                    call.respond(HttpStatusCode.NotFound, "{ success: false }")
                }
            }

            delete("/{id}") {
                try {
                    val id = call.parameters["id"]
                    productData.delete(id!!)
                    call.respond(HttpStatusCode.Created, "{ success: true }")
                } catch (e: DataException) {
                    call.respond(HttpStatusCode.NotFound, "{ success: false }")
                }
            }
        }
    }

}