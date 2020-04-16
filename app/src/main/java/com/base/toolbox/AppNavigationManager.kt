package com.base.toolbox

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentManager.OnBackStackChangedListener
import com.base.R
import com.base.activities.NActivity
import com.base.navigation.NavigationManager
import com.base.navigation.stateStack.NavigationState

/**
 * @author ThucPT on 3/15/2018.
 */

class AppNavigationManager(private var mActivity: NActivity?, frameId: Int) : NavigationManager {

    private var mFragmentManager: FragmentManager? = null
    private var currentPlaceholder: Int = 0

    init {
        currentPlaceholder = frameId
        mFragmentManager = mActivity?.supportFragmentManager
    }

    override fun addOnBackStackChangedListener(
            backStackChangedListener: OnBackStackChangedListener) {
        mFragmentManager?.addOnBackStackChangedListener(backStackChangedListener)
    }

    override fun removeOnBackStackChangedListener(
            onbackstackchangedlistener: OnBackStackChangedListener) {
        mFragmentManager?.removeOnBackStackChangedListener(onbackstackchangedlistener)
    }

    /**
     * Check whether can navigate or not.

     * @return true if can navigate, false otherwise.
     */
    private fun canNavigate(): Boolean {
        return mActivity != null && mActivity!!.canChangeFragmentManagerState()
    }

    override fun goBack(): Boolean {
        if (mActivity == null || (mActivity?.canChangeFragmentManagerState() == false)
                || mFragmentManager?.backStackEntryCount == 0)
            return false
        mFragmentManager?.popBackStack()
        // Even popped back stack, the fragment which is added without addToBackStack would be showing.
        // So we need to remove it manually
        val transaction = mFragmentManager?.beginTransaction()
        val currentFrag = mFragmentManager?.findFragmentById(currentPlaceholder)
        if (currentFrag != null) {
            transaction?.remove(currentFrag)
            transaction?.commit()
        }

        return true
    }

    override fun finishActivity() {
        mActivity?.finish()
    }

    override fun showPage(fragment: Fragment) = showPage(fragment, true, true)

    override fun showPage(fragment: Fragment, hasAnimation: Boolean, isAddBackStack: Boolean) {
        if (!canNavigate())
            return
        val transaction = mFragmentManager?.beginTransaction()
        if (hasAnimation)
            transaction?.setCustomAnimations(R.anim.fragment_enter,
                    R.anim.fragment_exit, R.anim.fragment_pop_enter,
                    R.anim.fragment_pop_exit)
        transaction?.replace(currentPlaceholder, fragment)
        val navigationState = NavigationState(currentPlaceholder)
        if (isAddBackStack) {
            transaction?.addToBackStack(navigationState.backStackName)
        }
        transaction?.commit()

    }

    override fun changeRootPage(fragment: Fragment, hasAnimation: Boolean) {
        if (!canNavigate())
            return
        val transaction = mFragmentManager?.beginTransaction()
        if (hasAnimation)
            transaction?.setCustomAnimations(R.anim.fragment_enter,
                    R.anim.fragment_exit, R.anim.fragment_pop_enter,
                    R.anim.fragment_pop_exit)
        transaction?.replace(currentPlaceholder, fragment)
        if (!isBackStackEmpty) {
            val first = mFragmentManager?.getBackStackEntryAt(0)
            first?.let {
                mFragmentManager?.popBackStack(it.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
        transaction?.commit()
    }

    /**
     * Terminate resource that keep by this class. Must call in
     * [Activity.onDestroy]
     */
    fun terminate() {
        mFragmentManager = null
        mActivity = null
    }


    override val isBackStackEmpty: Boolean
        get() {
            mFragmentManager?.backStackEntryCount?.let {
                return it <= 0
            }
            return false
        }


    override val activePage: Fragment?
        get() = mFragmentManager?.findFragmentById(currentPlaceholder)
}
