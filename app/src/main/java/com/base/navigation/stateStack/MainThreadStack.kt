package com.base.navigation.stateStack


import android.os.Looper
import java.util.*

/**
 * @author ThucPT on 3/15/2018.
 */

class MainThreadStack : Stack<NavigationState>() {

    @Synchronized
    override fun isEmpty(): Boolean {
        ensureOnMainThread()
        return super.isEmpty()
    }

    @Synchronized
    override fun peek(): NavigationState {
        ensureOnMainThread()
        return super.peek()
    }

    @Synchronized
    override fun pop(): NavigationState {
        ensureOnMainThread()
        return super.pop()
    }

    @Synchronized
    override fun push(obj: NavigationState): NavigationState {
        ensureOnMainThread()
        return super.push(obj)
    }

    companion object {
        private const val serialVersionUID = 1L
    }

    private fun ensureOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper())
            throw IllegalStateException(
                    "This method must be called from the UI thread.");
    }
}
