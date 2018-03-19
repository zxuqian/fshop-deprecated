package com.zxuqian.data

import com.mongodb.client.MongoCollection
import com.zxuqian.DATABASE_NAME
import com.zxuqian.mongoClient
import java.lang.reflect.ParameterizedType


interface GenericDAO<T> {

    fun get(id: String)

    fun getAll(): List<T>

    fun insert(t: T)

    fun update(t: T)

    fun delete(t: T)

}

class GenericDAOImpl<T>(val collection: MongoCollection<T>): GenericDAO<T> {

    override fun get(id: String) {

    }

    override fun getAll(): List<T> {
        return collection.find().toList()
    }

    override fun insert(t: T) {

    }

    override fun update(t: T) {

    }

    override fun delete(t: T) {

    }

}