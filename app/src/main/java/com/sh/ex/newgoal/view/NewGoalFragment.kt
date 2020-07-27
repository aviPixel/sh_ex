package com.sh.ex.newgoal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sh.ex.R
import com.sh.ex.newgoal.interfaces.INewGoal
import com.sh.ex.newgoal.view.adapter.ListNewGoalAdapter
import core.custom_ui.SpaceItemDecoration
import core.util.setStatusBarColor
import kotlinx.android.synthetic.main.fragment_new_goal.*

class NewGoalFragment : Fragment(), INewGoal.View, INewGoal.Api {

    companion object {
        fun newInstance(): NewGoalFragment {
            return NewGoalFragment().apply {
                val args = Bundle()
                arguments = args
            }
        }
    }

    private var newGoalAdapter: ListNewGoalAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_goal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        apiListGoal()
    }

    override fun initView() {
        activity?.let { act ->
            act.setStatusBarColor(R.color.bgYellow)

            newGoalAdapter = ListNewGoalAdapter(act, object : ListNewGoalAdapter.OnItemClickListener {
                override fun onItemClick() {}
            })
            rvNewGoal?.addItemDecoration(SpaceItemDecoration(3, resources.getDimensionPixelSize(R.dimen.app_margin), true, 0))
            rvNewGoal?.adapter = newGoalAdapter
        }
    }

    override fun apiListGoal() {
        val arrData = ArrayList<String>()
        arrData.add("Travel")
        arrData.add("Education")
        arrData.add("invest")
        arrData.add("Clothing")
        arrData.add("Education")
        newGoalAdapter?.setDataItem(arrData)
    }
}