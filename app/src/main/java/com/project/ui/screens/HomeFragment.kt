package com.project.ui.screens


import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.base.R
import com.base.actionBar.ActionBarControl
import com.base.actionBar.ActionBarController
import com.base.actionBar.info.ActionbarInfo
import com.base.actionBar.info.ActionbarLeftHandler
import com.base.fragments.BaseAppFragment
import com.project.ui.actionbars.ActionBarDefaultController
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * @author ThucPT on 3/15/2018.
 */
class HomeFragment : BaseAppFragment(), ActionBarControl, ActionbarInfo, ActionbarLeftHandler {

    override val leftEmptyResource: Int
        get() = R.drawable.ic_menu_white_24dp

    override val leftResource: Int
        get() = R.drawable.ic_arrow_back_white_24dp

    override fun onLeftHandled(): Boolean = false

    override val layoutRes: Int = R.layout.fragment_home


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detail.setOnClickListener {
            navigationManager?.showPage(DetailFragment())
        }

        collapsing.setOnClickListener {
            navigationManager?.showPage(CollapsingFragment())
        }

        callApi.setOnClickListener {
            api?.callApi("Demo", "", apiError = { requestId, e ->
                Toast.makeText(context, "request: $requestId -- error: ${e.response}", Toast.LENGTH_SHORT).show()

            }, apiSuccess = { requestId, response ->
                Toast.makeText(context, "request: $requestId -- response: ${response}", Toast.LENGTH_SHORT).show()
            })
        }
    }

    override val actionbarTitle: String = "Home Demo"

    override val actionBarController: ActionBarController = ActionBarDefaultController()

    override val actionBarLayoutRes: Int = R.layout.actionbar_default
}
