package com.zxuqian.data

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import com.zxuqian.DATABASE_NAME
import com.zxuqian.exceptions.DataException
import com.zxuqian.mongoClient
import io.ktor.http.parseQueryString
import org.bson.types.ObjectId
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*


const val ID_FEILD = "_id"

open class Model(var id: ObjectId = ObjectId(), var created: Date = Date(), var lastModified: Date = Date(), var version: Long = 1)

interface GeneralDAO<T> {

    fun getById(id: String): T

    fun getAll(): List<T>

    fun insert(t: T): T

    fun update(t: T)

    fun delete(id: String)

}

abstract class GeneralDAOImpl<T>(val collection: MongoCollection<T>): GeneralDAO<T> {

    private val type: Type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]

    override fun getById(id: String): T {
        return collection.find(eq(ID_FEILD, ObjectId(id))).first()
    }

    override fun getAll(): List<T> {
        return collection.find().toList()
    }

    override fun insert(t: T): T {
        try {
            collection.insertOne(t)
            return t
        } catch (e: Exception) {
            throw DataException("Insert ${getSimpleTypeName()} failed!")
        }

    }

    override fun delete(id: String) {
        val result = collection.deleteOne(eq(ID_FEILD, ObjectId(id)))
        if(result.deletedCount == 0L) {
            throw DataException("Delete ${getSimpleTypeName()} failed!")
        }
    }

    /**
     * Get the reified generic type name for logging
     */
    private fun getSimpleTypeName(): String {
        return type.typeName.substring(type.typeName.lastIndexOf(".") + 1)
    }

}