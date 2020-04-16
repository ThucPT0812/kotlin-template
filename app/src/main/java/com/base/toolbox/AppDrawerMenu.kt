package com.base.toolbox

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.base.R
import com.base.activities.NActivity
import com.base.activities.host.AppFragmentHost
import com.base.navigation.NavigationManager
import com.base.slideMenu.ActionMenu
import com.project.ui.screens.HomeFragment


/**
 * @author ThucPT on 3/15/2018.
 */

class AppDrawerMenu : ActionMenu, NavigationView.OnNavigationItemSelectedListener {

    private var drawer: DrawerLayout? = null
    private var navigationManager: NavigationManager? = null
    private var activity: NActivity? = null

    override val isShow: Boolean
        get() = drawer != null && drawer?.isDrawerOpen(GravityCompat.START) == true

    override fun initialize(activity: NActivity) {
        this.activity = activity
        drawer = activity.findViewById(R.id.drawerLayout)
        val toggle = ActionBarDrawerToggle(
                activity, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer?.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = activity.findViewById<NavigationView>(R.id.navigationView)
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this)
            initView(navigationView.getHeaderView(0))
        }

        if (activity is AppFragmentHost) {
            this.navigationManager = activity.navigationManager
        }
    }

    private fun initView(view: View) {
        // Todo findView
    }

    override fun hide() {
        if (drawer?.isDrawerOpen(GravityCompat.START) == true) {
            drawer?.closeDrawer(GravityCompat.START)
        }
    }

    override fun show() {
        drawer?.openDrawer(GravityCompat.START)
    }

    override fun destroy() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        drawer?.closeDrawer(GravityCompat.START)

        if (item.itemId == R.id.nav_home) {
            if (navigationManager?.activePage !is HomeFragment) {
                navigationManager?.changeRootPage(HomeFragment(), true)
            }
        }
        return true
    }

}
