package com.base.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * @author ThucPT on 3/15/2018.
 */
interface NavigationManager {
    /**
     * Pop fragment in stack if stack isn't empty.
     *
     * @return true if success, false otherwise. (maybe: stack is empty,
     * activity is in onSaveInstance())
     */
    fun goBack(): Boolean

    val activePage: Fragment?

    fun finishActivity()

    fun showPage(fragment: Fragment)

    fun showPage(fragment: Fragment, hasAnimation: Boolean, isAddBackStack: Boolean)

    fun changeRootPage(fragment: Fragment, hasAnimation: Boolean)

    val isBackStackEmpty: Boolean

    fun addOnBackStackChangedListener(
            backStackChangedListener: FragmentManager.OnBackStackChangedListener)

    fun removeOnBackStackChangedListener(
            onbackstackchangedlistener: FragmentManager.OnBackStackChangedListener)
}