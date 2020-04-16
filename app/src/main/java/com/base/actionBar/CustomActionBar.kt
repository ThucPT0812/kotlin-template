package com.base.actionBar

import android.app.Activity
import android.support.v4.app.Fragment
import com.base.activities.NActivity
import com.base.navigation.NavigationManager

/**
 * @author ThucPT on 3/15/2018.
 */
interface CustomActionBar {
    /**
     * Initialize Actionbar for Activity. Should call in
     * [Activity.onCreate] after setContentView(...).
     *
     * @param activity
     */
    fun initialize(activity: NActivity)

    /**
     * Initialize Actionbar for Activity. Should call in
     * [Activity.onCreate] after setContentView(...).
     *
     * @param navigationManager
     */
    fun initialize(navigationManager: NavigationManager)

    /**
     * Sync state actionBar for specific fragment.
     *
     * @param activePage Fragment that current displayed.
     */
    fun syncActionBar(activePage: Fragment)

    val actionbarController: ActionBarController?

    fun setTitle(title: String)

    fun hide()

    fun show()

    fun destroy()
}