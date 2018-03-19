package com.zxuqian.utils

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.bson.types.ObjectId
import java.lang.reflect.Type

class ObjectIdSerializer: JsonSerializer<ObjectId> {
    override fun serialize(src: ObjectId?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return JsonPrimitive(src.toString())
    }

}