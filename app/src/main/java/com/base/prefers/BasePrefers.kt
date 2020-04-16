package com.base.prefers

import android.content.Context
import android.content.SharedPreferences

/**
 * @author ThucPT on 3/15/2018.
 */

abstract class BasePrefers protected constructor(context: Context) {

    protected var mEditor: SharedPreferences.Editor
    var sharedPreference: SharedPreferences
        protected set

    protected abstract val fileNamePrefers: String

    init {
        val applicationContext = context.applicationContext
        sharedPreference = applicationContext.getSharedPreferences(fileNamePrefers,
                Context.MODE_PRIVATE)
        mEditor = sharedPreference.edit()
    }
}
