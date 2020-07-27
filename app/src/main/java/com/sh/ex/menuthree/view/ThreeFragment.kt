package com.sh.ex.menuthree.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sh.ex.R
import com.sh.ex.menuthree.interfaces.IMenuThree
import com.sh.ex.menuthree.view.adapter.ListAchievementAdapter
import core.custom_ui.SpaceItemDecoration
import core.util.setStatusBarColor
import kotlinx.android.synthetic.main.fragment_menu_three.*

class ThreeFragment : Fragment(), IMenuThree.View, IMenuThree.Api {

    companion object {
        fun newInstance(): ThreeFragment {
            return ThreeFragment().apply {
                val args = Bundle()
                arguments = args
            }
        }
    }

    private var achievementAdapter: ListAchievementAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        apiListAchievement()
    }

    override fun initView() {
        activity?.let { act ->
            act.setStatusBarColor(R.color.bgYellow)

            achievementAdapter = ListAchievementAdapter(act, object : ListAchievementAdapter.OnItemClickListener {
                override fun onItemClick() {}
            })
            rvAchievement?.addItemDecoration(SpaceItemDecoration(
                3,
                resources.getDimensionPixelSize(R.dimen.app_margin_8),
                true,
                0))
            rvAchievement?.adapter = achievementAdapter
        }
    }

    override fun apiListAchievement() {
        val arrData = ArrayList<String>()
        for (i in 0..7) {
            arrData.add("")
        }
        achievementAdapter?.setDataItem(arrData)
    }
}