package com.base.toolbox

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.ActionBar
import com.base.actionBar.ActionBarControl

import com.base.actionBar.ActionBarController
import com.base.actionBar.CustomActionBar
import com.base.activities.host.AppFragmentHost
import com.base.activities.NActivity
import com.base.navigation.NavigationManager

/**
 * @author ThucPT on 3/15/2018.
 */

class AppActionBar : CustomActionBar, FragmentManager.OnBackStackChangedListener {

    private var navigationManager: NavigationManager? = null
    private var actionBar: ActionBar? = null
    private var mActionBarController: ActionBarController? = null

    override val actionbarController: ActionBarController?
        get() = mActionBarController

    override fun initialize(activity: NActivity) {
        if (activity is AppFragmentHost) {
            this.navigationManager = (activity as AppFragmentHost).navigationManager
            this.navigationManager?.addOnBackStackChangedListener(this)
        }
        this.actionBar = activity.supportActionBar
    }

    override fun initialize(navigationManager: NavigationManager) {
        this.navigationManager = navigationManager
        this.navigationManager?.addOnBackStackChangedListener(this)
    }

    override fun syncActionBar(activePage: Fragment) {
        if (activePage is ActionBarControl) {
            mActionBarController = (activePage as ActionBarControl).actionBarController
        } else {
            return
        }
        actionbarController?.initialize(actionBar, navigationManager)
        val resId = actionbarController?.findResourceIdForActionbar(activePage) ?: 0
        actionbarController?.findChildViews(resId)
        actionbarController?.setupChildViews()
        actionbarController?.syncChildView(activePage)
    }

    override fun setTitle(title: String) {
        if (actionbarController != null) {
            actionbarController?.setTitle(title)
        } else if (actionBar != null) {
            actionBar?.title = title
        }
    }

    override fun hide() {
        actionbarController?.hideActionbar() ?: actionBar?.hide()
    }

    override fun show() {
        actionbarController?.showActionbar() ?: actionBar?.show()
    }

    override fun destroy() {
        this.navigationManager?.removeOnBackStackChangedListener(this)
    }

    override fun onBackStackChanged() {
        actionbarController?.updateStackChildViews()
    }
}
