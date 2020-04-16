package com.base.activities

import android.os.Bundle
import android.support.annotation.IdRes
import android.view.KeyEvent
import com.base.R
import com.base.actionBar.CustomActionBar
import com.base.activities.host.AppFragmentHost
import com.base.slideMenu.ActionMenu
import com.base.api.Api
import com.base.navigation.NavigationManager
import com.base.toolbox.AppActivityController

/**
 * @author ThucPT on 3/15/2018.
 */

abstract class BaseAppActivity : NActivity(), AppFragmentHost {

    protected lateinit var mDelegate: AppActivityController

    open val contentLayout: Int
        @IdRes
        get() = R.id.contentLayout


    override val api: Api
        get() = mDelegate.api

    override val actionBar: CustomActionBar?
        get() = mDelegate.actionBar

    override val actionMenu: ActionMenu?
        get() = mDelegate.actionMenu

    override val navigationManager: NavigationManager
        get() = mDelegate.navigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        mDelegate = AppActivityController(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDelegate.doOnDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val isHandled = mDelegate.doDuringOnKeyDown(keyCode)

        return isHandled || super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!mDelegate.doOnBackPress())
            super.onBackPressed()
    }
}
