package com.project.ui.actionbars

import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.base.R
import com.base.actionBar.ActionBarControl
import com.base.actionBar.ActionBarController
import com.base.actionBar.info.ActionbarInfo
import com.base.actionBar.info.ActionbarLeftHandler
import com.base.fragments.BaseAppFragment
import com.base.navigation.NavigationManager

/**
 * @author ThucPT on 3/15/2018.
 */

class ActionBarDefaultController : ActionBarController {

    private var navigationManager: NavigationManager? = null
    private var actionBar: ActionBar? = null
    private var title: TextView? = null
    private var actionLeft: ImageView? = null

    override fun initialize(actionBar: ActionBar?, navigationManager: NavigationManager?) {

        this.navigationManager = navigationManager
        this.actionBar = actionBar
    }

    override fun findResourceIdForActionbar(fragment: Fragment): Int {
        return if (fragment is ActionBarControl) {
            (fragment as ActionBarControl).actionBarLayoutRes
        } else 0
    }

    override fun findChildViews(layoutRes: Int) {
        if (actionBar == null) return
        actionBar?.setDisplayShowHomeEnabled(false)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setCustomView(layoutRes)
        actionBar?.setDisplayShowCustomEnabled(true)
        val view = actionBar?.customView
        title = view?.findViewById(R.id.actionbarTitle)
        actionLeft = view?.findViewById(R.id.actionbarLeft)
    }

    override fun setupChildViews() {
        setUpBack()
    }

    override fun updateStackChildViews() {
        setupResourceForBack()
    }

    override fun syncChildView(activePage: Fragment) {
        syncTitle(activePage)
    }

    override fun setTitle(title: String) {
        this.title?.text = title
    }

    private fun syncTitle(activePage: Fragment) {
        if (title == null) return

        if (activePage is ActionbarInfo) {
            val actionbarInfo = activePage as ActionbarInfo
            val titleStr = actionbarInfo.actionbarTitle
            title?.text = titleStr
        }
    }

    private fun setUpBack() {
        if (actionLeft == null) return
        setupResourceForBack()
        actionLeft?.setOnClickListener(View.OnClickListener {
            val fragment = navigationManager?.activePage
            if (navigationManager?.isBackStackEmpty == true && fragment is BaseAppFragment) {
                fragment.actionMenu?.let {
                    it.show()
                    return@OnClickListener
                }
            }

            // Allow the current fragment controls action back
            if (fragment != null && fragment is ActionbarLeftHandler) {
                if ((fragment as ActionbarLeftHandler).onLeftHandled()) {
                    return@OnClickListener
                }
            }
            // Normal show slide menu
            if (navigationManager?.goBack() == false) {
                navigationManager?.finishActivity()
            }
        })
    }

    private fun setupResourceForBack() {
        if (actionLeft == null) return

        val fragment = navigationManager?.activePage
        if (navigationManager?.isBackStackEmpty == true) {

            val res = if (fragment is ActionbarLeftHandler) fragment.leftEmptyResource
            else android.R.color.transparent
            actionLeft?.setImageResource(res)
        } else {
            val res = if (fragment is ActionbarLeftHandler) fragment.leftResource
            else R.drawable.ic_arrow_back_white_24dp
            actionLeft?.setImageResource(res)
        }
    }

    override fun hideActionbar() {
        actionBar?.hide()
    }

    override fun showActionbar() {
        actionBar?.show()
    }
}
