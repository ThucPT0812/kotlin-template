package com.base.fragments

/**
 * @author ThucPT
 */

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.actionBar.CustomActionBar
import com.base.activities.host.AppFragmentHost
import com.base.api.Api
import com.base.dialog.LoadingDialogFragment
import com.base.slideMenu.ActionMenu

/**
 * @author ThucPT on 3/15/2018.
 * This is base App fragment.
 * It contains some default attributes:  Api, menu
 * NavigationManager, Actionbar
 */
abstract class BaseAppFragment : NFragment() {

    var api: Api? = null

    var actionBar: CustomActionBar? = null

    var actionMenu: ActionMenu? = null

    protected lateinit var activity: Activity

    private var mHandler: Handler? = null

    @get:LayoutRes
    abstract val layoutRes: Int

    private var mProgressDialog: ProgressDialog? = null

    init {
        arguments = Bundle()
    }

    @CallSuper
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.activity = context as Activity
        if (mPageFragmentHost is AppFragmentHost) {
            api = (mPageFragmentHost as AppFragmentHost).api
            actionBar = (mPageFragmentHost as AppFragmentHost).actionBar
            actionMenu = (mPageFragmentHost as AppFragmentHost).actionMenu
        }
    }

    /**
     * Shouldn't override this function...Use [.getLayoutRes]
     */
    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = super.onCreateView(inflater, container, savedInstanceState)
        if (layoutRes != 0) {
            view = inflater.inflate(layoutRes, container, false)
        }
        return view
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (actionBar == null && mPageFragmentHost is AppFragmentHost) {
            actionBar = (mPageFragmentHost as AppFragmentHost).actionBar
            actionMenu = (mPageFragmentHost as AppFragmentHost).actionMenu
        }

        actionBar?.syncActionBar(this)

        if (isHasActionbar) {
            actionBar?.show()
        } else {
            actionBar?.hide()
        }
    }

    protected fun showLoading() {
        val loading = childFragmentManager.findFragmentByTag(LOADING_TAG)
        if (loading != null) return

        LoadingDialogFragment().show(childFragmentManager, LOADING_TAG)
    }

    protected fun hideLoading() {

        if (mHandler == null) mHandler = Handler(Looper.getMainLooper())
        mHandler?.postDelayed(Runnable {
            val loading = childFragmentManager.findFragmentByTag(LOADING_TAG) ?: return@Runnable

            (loading as DialogFragment).dismissAllowingStateLoss()
        }, 500)
    }

    protected fun showDialog(dialog: DialogFragment?) {
        if (dialog == null) return

        dialog.show(childFragmentManager, DIALOG_TAG)
    }

    fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(activity)
            mProgressDialog?.setCancelable(false)
            mProgressDialog?.setMessage("Loading...")
        }

        mProgressDialog?.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog?.isShowing == true) {
            mProgressDialog?.dismiss()
        }
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()

        mHandler?.removeCallbacksAndMessages(null)
        mHandler = null
    }

    companion object {

        val DIALOG_TAG = "dialog_tag"
        val LOADING_TAG = "loading_tag"
    }
}
