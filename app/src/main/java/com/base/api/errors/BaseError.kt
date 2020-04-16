package com.base.api.errors

/**
 * @author ThucPT on 3/15/2018.
 */
open class BaseError(open val requestId: String, open val response: String) : Exception()
