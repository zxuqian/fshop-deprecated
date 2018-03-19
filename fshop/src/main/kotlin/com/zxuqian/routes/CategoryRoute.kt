package com.zxuqian.routes

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zxuqian.data.Category
import com.zxuqian.data.CategoryData
import com.zxuqian.exceptions.DataException
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory


fun Route.category() {

    val categoryData = CategoryData()

    route("/api") {
        route("category") {

            get("/") {
                val logger = LoggerFactory.getLogger("GET /category")

                call.respond(categoryData.getAll())
            }

            post("/") {
                val logger = LoggerFactory.getLogger("POST /category")

                try {
                    val category = call.receive<Category>()

                    categoryData.insert(category)

                    call.respond(HttpStatusCode.Created, Message(true))
                } catch (e: DataException) {
                    call.respond(HttpStatusCode.InternalServerError, "{ success: false }")
                }
            }

            put("/{id}") {
                //val id = call.parameters["id"].let { it }

                try {
                    val id = call.parameters["id"].let { ObjectId(it) }
                    val category = call.receive<Category>()
                    category.id = id
                    categoryData.update(category)
                    call.respond(HttpStatusCode.OK, Message(true))
                } catch (e: DataException) {
                    call.respond(HttpStatusCode.NotFound, "{ success: false }")
                }


            }

            delete("/{id}") {
                try {
                    val id = call.parameters["id"]
                    categoryData.delete(id!!)
                    call.respond(HttpStatusCode.OK, "{ success: true }")
                } catch (e: DataException) {
                    call.respond(HttpStatusCode.NotFound, "{ success: false }")
                }

            }
        }

    }

}