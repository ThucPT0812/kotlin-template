package com.base.actionBar

import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import com.base.navigation.NavigationManager

/**
 * @author ThucPT on 3/15/2018.
 */
interface ActionBarController {

    fun initialize(actionBar: ActionBar?, navigationManager: NavigationManager?)

    fun findResourceIdForActionbar(fragment: Fragment): Int

    fun findChildViews(@LayoutRes layoutRes: Int)

    fun setupChildViews()

    fun updateStackChildViews()

    fun syncChildView(activePage: Fragment)

    fun setTitle(title: String)

    fun hideActionbar()

    fun showActionbar()
}