package com.base.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.activities.host.NFragmentHost
import com.base.dialog.listener.OnActionInDialogListener
import com.base.navigation.NavigationManager

/**
 * @author ThucPT on 3/15/2018.
 */
abstract class NFragment : Fragment(), OnActionInDialogListener, OnFragmentResultListener {

    protected var mPageFragmentHost: NFragmentHost? = null
    private var mSaveInstanceStateCalled: Boolean = false
    protected var navigationManager: NavigationManager? = null

    protected open val isHasActionbar: Boolean
        get() = true

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is NFragmentHost) {
            mPageFragmentHost = context
            navigationManager = mPageFragmentHost?.navigationManager
        }
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mSaveInstanceStateCalled = false
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSaveInstanceStateCalled = false
    }

    /**
     * Shouldn't override this function...Use [.getLayoutRes]
     */
    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mSaveInstanceStateCalled = false
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        mSaveInstanceStateCalled = false
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        mSaveInstanceStateCalled = false
    }

    /**
     * Method check state of fragment. Can not change state of fragment (like:
     * navigate in fragment, change layout...)
     *
     * @return true Valid for change state, otherwise not valid
     */
    fun canChangeFragmentManagerState(): Boolean {
        val activity = activity
        return !(mSaveInstanceStateCalled || activity == null || activity.isFinishing)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mSaveInstanceStateCalled = true
    }

    override fun onDialogResult(requestCode: Int, action: Int, extraData: Intent?) {
        //TODO Implement the default action listener from dialog
    }

    override fun onFragmentResult(requestCode: Int, action: Int, extraData: Intent?) {
        //TODO Implement the default action listener from target fragment
    }
}
