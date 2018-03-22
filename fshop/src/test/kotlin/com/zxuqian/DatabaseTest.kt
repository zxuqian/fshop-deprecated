package com.zxuqian

import com.mongodb.client.model.Projections.*
import com.zxuqian.data.Category
import com.zxuqian.data.CategoryData
import com.zxuqian.data.GeneralDAO
import com.zxuqian.data.GeneralDAOImpl
import org.bson.types.ObjectId
import org.junit.Test

class DatabaseTest {

    @Test
    fun testCategoryDAO() {

        val dao = CategoryData()
//        val cate = Category("cccc22")
//        dao.insert(cate)
//        println(cate.id)

//        val categoryList = dao.getAll()
//        println(categoryList)
//
//        dao.update(Category("aabbcc", ObjectId("5aae26d80b320194ba7e2131")))

        //dao.addChild("5aa35368079bff7973ad33d4", Category("child-Test"))

//        dao.testProjections().forEach {
//            print(it.lastModified)
//        }

//        var a = mongoClient.getDatabase(DATABASE_NAME).getCollection(CATEGORY_COLLECTION).find().projection(fields(exclude("lastModified")))
//
//        var b = a.toList()
//
//        println(b)
    }

}