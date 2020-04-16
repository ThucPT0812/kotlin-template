package com.base.api.listener

/**
 * @author ThucPT on 3/15/2018.
 */

interface ApiResponseListener<in T> {

    fun onResponse(requestId: String, response: T)

    fun onError(requestId: String, e: Exception)

}
