package com.project.ui.activities

import android.os.Bundle
import com.base.R
import com.base.activities.BaseAppActivity
import com.project.ui.screens.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * @author ThucPT on 3/15/2018.
 */

class MainActivity : BaseAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            mDelegate.doOnInitActionBar(this)
        }

        if (navigationView !=null) {
            mDelegate.doOnInitMenu(this)
        }

        if (navigationManager.activePage == null) {
            navigationManager.changeRootPage(HomeFragment(), false)
        }
    }
}
