package com.base.actionBar.info

/**
 * @author ThucPT on 3/15/2018.
 */
interface ActionbarLeftHandler {

    fun onLeftHandled(): Boolean

    val leftEmptyResource: Int

    val leftResource: Int
}
