package com.zxuqian.routes

data class Message<T>(var success: Boolean = false, var obj:T? = null)