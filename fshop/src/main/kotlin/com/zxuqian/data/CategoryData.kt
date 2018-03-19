package com.zxuqian.data

import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Updates.*
import com.zxuqian.CATEGORY_COLLECTION
import com.zxuqian.DATABASE_NAME
import com.zxuqian.exceptions.DataException
import com.zxuqian.mongoClient
import org.bson.types.ObjectId


// Fields have to be var, otherwise they would not allow to set new values
data class Category(var name: String = "") : Model()

interface CategoryDAO : GeneralDAO<Category>

class CategoryData : CategoryDAO,
        GeneralDAOImpl<Category>(mongoClient.getDatabase(DATABASE_NAME).getCollection(CATEGORY_COLLECTION,
                Category::class.java)) {

    override fun update(category: Category) {

        val result = collection.updateOne(eq(ID_FEILD, category.id), combine(
                set("name", category.name),
                currentDate("lastModified"),
                inc("version", 1)
        ))

        if(result.modifiedCount < 1) {
            throw DataException("Update category failed")
        }

    }

}