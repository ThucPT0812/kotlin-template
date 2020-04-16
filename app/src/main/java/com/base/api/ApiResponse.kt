package com.base.api

/**
 * @author ThucPT on 3/15/2018.
 */

data class ApiResponse<T>(var code: Int, var data: T?) {

    val isSuccess: Boolean = (code == 0)
}
