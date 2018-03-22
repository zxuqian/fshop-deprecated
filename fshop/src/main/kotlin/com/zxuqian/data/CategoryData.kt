package com.zxuqian.data

import com.mongodb.client.MongoIterable
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Projections
import com.mongodb.client.model.Projections.*
import com.mongodb.client.model.Updates.*
import com.zxuqian.CATEGORY_COLLECTION
import com.zxuqian.DATABASE_NAME
import com.zxuqian.exceptions.DataException
import com.zxuqian.mongoClient
import org.bson.types.ObjectId


// Fields have to be var, otherwise they would not allow to set new values
data class Category(var name: String = "", var parentId: ObjectId? = null) : Model()

interface CategoryDAO : GeneralDAO<Category> {

    /**
     * Add the sub-category to the database, along with parent id to construct a tree structure
     *
     * @param parentId The parent category id that this sub-category belongs to
     * @param subCategory The sub-category instance
     * @throws DataException Throws a custom exception indicating internal failure
     */
    fun addChild(parentId: String, subCategory: Category)

}

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

    override fun addChild(parentId: String, subCategory: Category) {
        try {
            subCategory.parentId = ObjectId(parentId)
            collection.insertOne(subCategory)
        } catch (e: Exception) {
            throw DataException("Add sub category failed")
        }
    }

    fun testProjections(): MongoIterable<Category> {

        var a = collection.find()
        var b = a.projection(Projections.fields(Projections.exclude("lastModified")))
        var c = b.toList()

        return b
    }

}