package com.project.ui.screens

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.base.R
import com.base.fragments.BaseAppFragment
import com.project.ui.adapter.ItemAdapter
import kotlinx.android.synthetic.main.actionbar_default.*
import kotlinx.android.synthetic.main.fragment_collapsing.*

/**
 * @author ThucPT on 3/15/2018.
 */

class CollapsingFragment : BaseAppFragment() {

    override val layoutRes: Int = R.layout.fragment_collapsing

    override val isHasActionbar: Boolean
        get() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val adapter = ItemAdapter()
        adapter.items.addAll(dummyData())
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(divider)
        recyclerView.adapter = adapter

        adapter.setItemClick { position, item ->
            Toast.makeText(context,"click position: $position -- data: $item", Toast.LENGTH_SHORT).show()
        }

        actionbarTitle.text = "Collapsing Demo"
        actionbarLeft.setOnClickListener({
            if (navigationManager?.goBack() == false) {
                navigationManager?.finishActivity()
            }
        })
    }

    private fun dummyData(): ArrayList<String> {
        val result = ArrayList<String>()
        for (i in 1..100) {
            result.add("Item $i")
        }
        return result
    }
}

