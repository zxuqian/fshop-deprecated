package com.zxuqian.data.product

import com.zxuqian.DATABASE_NAME
import com.zxuqian.PRODUCT_COLLECTION
import com.zxuqian.mongoClient
import org.bson.Document
import org.bson.types.ObjectId


class ProductData {
    val productCollection = mongoClient.getDatabase(DATABASE_NAME).getCollection(PRODUCT_COLLECTION)

    fun createProduct(title: String, price: String) {
        val doc = Document("title", title).append("price", price)
        productCollection.insertOne(doc) { result, t ->
            //t.printStackTrace()
        }
    }

    fun findAllProducts() {
        productCollection.count { count, t ->
            println(count)
            count
        }
    }
}
