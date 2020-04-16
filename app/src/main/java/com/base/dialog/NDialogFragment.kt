package com.base.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.DialogFragment
import com.base.dialog.toolbox.NotifyUtil

/**
 * @author ThucPT on 3/15/2018.
 */
abstract class NDialogFragment : DialogFragment() {

    protected var mRequestCode: Int = 0

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            getDataFrom(savedInstanceState)
        } else {
            val bundle = arguments
            if (bundle != null)
                getDataFrom(bundle)
        }
    }

    @CallSuper
    protected open fun getDataFrom(bundle: Bundle) {
        mRequestCode = bundle.getInt(EXTRA_REQUEST_CODE)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_REQUEST_CODE, mRequestCode)
    }

    @CallSuper
    override fun onCancel(dialog: DialogInterface?) {
        NotifyUtil.notifyAction(false, this, null, mRequestCode, ACTION_CANCEL)
    }

    companion object {

        val ACTION_CANCEL = -1
        private val EXTRA_REQUEST_CODE = "extra_request_code"

        fun makeBundle(requestCode: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(EXTRA_REQUEST_CODE, requestCode)
            return bundle
        }
    }
}
