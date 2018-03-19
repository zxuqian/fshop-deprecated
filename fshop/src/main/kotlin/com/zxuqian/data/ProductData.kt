package com.zxuqian.data

import com.mongodb.client.model.Filters.*
import com.mongodb.client.model.Updates.*
import com.zxuqian.DATABASE_NAME
import com.zxuqian.PRODUCT_COLLECTION
import com.zxuqian.exceptions.DataException
import com.zxuqian.mongoClient
import org.bson.Document
import org.bson.types.ObjectId


data class Product(val name: String? = null, val description: String? = null,
                   var details: String? = null, var price: Double) : Model()

interface ProductDAO: GeneralDAO<Product>

class ProductData : ProductDAO, GeneralDAOImpl<Product>(
        mongoClient.getDatabase(DATABASE_NAME).getCollection(PRODUCT_COLLECTION,
                Product::class.java)) {

    override fun update(product: Product) {
        val result = collection.updateOne(eq(ID_FEILD, product.id),
                combine(
                        set("name", product.name),
                        set("description", product.description),
                        set("details", product.details),
                        set("price", product.price),
                        currentDate("lastModified"),
                        inc("version", 1)
                ))
        if(result.modifiedCount < 1) {
            throw DataException("update product failed")
        }
    }


}
