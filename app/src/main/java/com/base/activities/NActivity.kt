package com.base.activities

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity

/**
 * @author ThucPT on 3/15/2018.
 */
abstract class NActivity : AppCompatActivity() {

    protected var mSaveInstanceStateCalled: Boolean = false

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSaveInstanceStateCalled = false
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        mSaveInstanceStateCalled = false
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        mSaveInstanceStateCalled = false
    }

    fun canChangeFragmentManagerState(): Boolean {
        return !(mSaveInstanceStateCalled || isFinishing)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mSaveInstanceStateCalled = true
    }

    companion object {
        protected val TAG = "Activity"
    }
}
