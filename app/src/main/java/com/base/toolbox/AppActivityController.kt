package com.base.toolbox

import android.view.KeyEvent
import com.base.actionBar.info.ActionbarLeftHandler
import com.base.actionBar.CustomActionBar
import com.base.activities.host.AppFragmentHost
import com.base.activities.BaseAppActivity
import com.base.activities.NActivity
import com.base.slideMenu.ActionMenu
import com.base.api.Api
import com.base.navigation.NavigationManager
import com.project.MyApplication


/**
 * @author ThucPT on 3/15/2018.
 */
class AppActivityController(activity: BaseAppActivity) : AppFragmentHost {


    private val mApi = MyApplication.get().api

    private var mActionBar: CustomActionBar? = null
    private var mActionMenu: ActionMenu? = null

    private val mNavigationManager = AppNavigationManager(activity, activity.contentLayout)


    override val navigationManager: NavigationManager
        get() = mNavigationManager

    override val api: Api
        get() = mApi

    override val actionBar: CustomActionBar?
        get() = mActionBar

    override val actionMenu: ActionMenu?
        get() = mActionMenu

    fun doOnInitActionBar(activity: NActivity) {
        mActionBar = AppActionBar()
        mActionBar?.initialize(activity)
    }

    fun doOnInitMenu(activity: NActivity) {
        mActionMenu = AppDrawerMenu()
        mActionMenu?.initialize(activity)
    }

    fun doOnDestroy() {
        mNavigationManager.terminate()
    }

    /**
     * @return true if handled, else return false
     */
    fun doDuringOnKeyDown(keyCode: Int): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            callBackHandlerOnActivePage()
        } else false

    }

    fun doOnBackPress(): Boolean {
        return callBackHandlerOnActivePage()
    }

    private fun callBackHandlerOnActivePage(): Boolean {

        if (mActionMenu?.isShow == true) {
            mActionMenu?.hide()
            return true
        }

        val activePage = mNavigationManager.activePage

        var isHandled = false

        if (activePage != null && activePage is ActionbarLeftHandler) {
            isHandled = (activePage as ActionbarLeftHandler).onLeftHandled()
        }

        if (!isHandled)
            isHandled = mNavigationManager.goBack()

        return isHandled
    }
}
