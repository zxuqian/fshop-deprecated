package com.zxuqian.data.product

import com.mongodb.client.FindIterable
import com.zxuqian.CATEGORY_COLLECTION
import com.zxuqian.DATABASE_NAME
import com.zxuqian.mongoClient
import com.zxuqian.pojoCodecRegistry
import org.bson.Document
import org.bson.types.ObjectId
import java.util.concurrent.TimeUnit

// Fields have to be var, otherwise they would not allow to set new values
data class Category(var name: String = "", var id: ObjectId? = null)

class CategoryData {

    private val categoryCollection = mongoClient.getDatabase(DATABASE_NAME).getCollection(CATEGORY_COLLECTION, Category::class.java)

    fun createCategory(name: String) {
        val category = Category(name)
        categoryCollection.insertOne(category)
    }

    fun findAllCategories(): FindIterable<Category> {
        return categoryCollection.find()
    }
}