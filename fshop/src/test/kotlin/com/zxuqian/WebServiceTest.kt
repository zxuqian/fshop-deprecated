package com.zxuqian

import com.zxuqian.data.Category
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.*
import io.netty.handler.codec.http.HttpHeaders.addHeader
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class WebServiceTest {

    @Test
    fun testRequest() = withTestApplication(Application::main) {
        //        with(handleRequest(HttpMethod.Get, "/")) {
//
//            println(response.content)
//        }
//
//        with(handleRequest(HttpMethod.Get, "/index.html")) {
//            assertFalse(requestHandled)
//        }
//
//        with(handleRequest(HttpMethod.Get, "/api/category") { addHeader("accept", "application/json")}) {
//            println(response.content)
//        }
//
//        with(handleRequest(HttpMethod.Post, "/api/category") {
//            addHeader("content-type", "application/json")
//            addHeader("accept", "application/json")
//            body = """
//                {
//                    name: "test from test"
//                }
//            """.trimIndent()
//        }) {
//            println(response.content)
//        }

//        with(handleRequest(HttpMethod.Delete, "/api/category/5aa35366079bff7973ad33d2")) {
//            println(response.content)
//        }
//
//        with(handleRequest(HttpMethod.Put, "/api/category/5aa35368079bff7973ad33d4") {
//            addHeader("content-type", "application/json")
//            addHeader("accept", "application/json")
//            body = """
//                {
//                    name: "test update"
//                }
//            """.trimIndent()
//        }) {
//            println(response.content)
//        }
    }


}
