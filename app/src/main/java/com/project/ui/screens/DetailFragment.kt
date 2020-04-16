package com.project.ui.screens


import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.base.R
import com.base.actionBar.ActionBarControl
import com.base.actionBar.ActionBarController
import com.base.actionBar.info.ActionbarInfo
import com.base.fragments.BaseAppFragment
import com.base.util.ImageUtil
import com.project.ui.actionbars.ActionBarDefaultController
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : BaseAppFragment(), ActionBarControl, ActionbarInfo {

    override val layoutRes: Int = R.layout.fragment_detail

    override val actionbarTitle: String = "Detail Demo"

    override val actionBarController: ActionBarController = ActionBarDefaultController()

    override val actionBarLayoutRes: Int = R.layout.actionbar_default

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { ImageUtil.loadAvatarImage(it,"http://genk2.vcmedia.vn/DlBlzccccccccccccE5CT3hqq3xN9o/Image/2012/12/8X/ca-thang-tu-1-151f0.jpg",imageView = imageView) }
        detail.setOnClickListener({
            Toast.makeText(context,"click", Toast.LENGTH_SHORT).show()
        })
    }
}
