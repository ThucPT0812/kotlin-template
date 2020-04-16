package com.base.activities.host

import com.base.actionBar.CustomActionBar
import com.base.api.Api

/**
 * @author ThucPT on 3/15/2018.
 */
interface AppFragmentHost : NFragmentHost, NMenuHost {

    val api: Api

    val actionBar: CustomActionBar?
}
