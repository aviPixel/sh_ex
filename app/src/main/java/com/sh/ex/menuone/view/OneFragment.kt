package com.sh.ex.menuone.view

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sh.ex.R
import com.sh.ex.mainmenu.view.MainActivity
import com.sh.ex.menuone.interfaces.IMenuOne
import com.sh.ex.menuone.model.GoalModel
import com.sh.ex.menuone.view.adapter.ListGoalAdapter
import com.sh.ex.menuone.view.adapter.ListOfferAdapter
import com.sh.ex.menuone.view.adapter.ListSuggestAdapter
import core.util.setStatusBarColor
import kotlinx.android.synthetic.main.fragment_menu_one.*


class OneFragment : Fragment(), IMenuOne.View, IMenuOne.Navigation, IMenuOne.Api {

    companion object {
        fun newInstance(): OneFragment {
            return OneFragment().apply {
                val args = Bundle()
                arguments = args
            }
        }
    }

    private var rvGoalAdapter: ListGoalAdapter? = null
    private var rvOfferAdapter: ListOfferAdapter? = null
    private var rvSuggestAdapter: ListSuggestAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        apiListGoal()
        apiListOffer()
        apiListSuggest()
    }

    override fun initView() {
        activity?.let { act ->
            act.setStatusBarColor(R.color.bgYellow)

            val layoutManager = LinearLayoutManager(act, RecyclerView.HORIZONTAL, false)
            rvListGoal?.layoutManager = layoutManager
            rvGoalAdapter = ListGoalAdapter(act, object : ListGoalAdapter.OnItemClickListener {
                override fun onItemClick(){}
            })
            rvListGoal?.adapter = rvGoalAdapter

            rvOfferAdapter = ListOfferAdapter(act, object : ListOfferAdapter.OnItemClickListener {
                    override fun onItemClick() {}
                })
            rvListOffer?.adapter = rvOfferAdapter

            rvSuggestAdapter = ListSuggestAdapter(act, object : ListSuggestAdapter.OnItemClickListener {
                    override fun onItemClick() {}
                })
            rvListSuggest?.adapter = rvSuggestAdapter

            val strPrice = "37,500"
            val s = "All Saving $strPrice THB"
            val ssBuilder = SpannableStringBuilder(s)
            val largeSizeText = RelativeSizeSpan(2.0f)
            ssBuilder.setSpan(
                largeSizeText, // Span to add
                s.indexOf(strPrice), // Start of the span (inclusive)
                s.indexOf(strPrice) + strPrice.length, // End of the span (exclusive)
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE // Do not extend the span when text add later
            );
            tvPriceGoal.text = ssBuilder

            btnNewGoal?.setOnClickListener {
                (act as MainActivity?)?.showNewGoalFragment()
            }
        }
    }

    override fun apiListGoal() {
        val arrListGoal = ArrayList<GoalModel>()
        val obj1 = GoalModel().apply {
            title = "ไปเที่ยวญี่ปุ่น"
            feel = "Good"
            dayLeft = 45
            currentPrice = 16500
            totalPrice = 20000
        }
        val obj2 = GoalModel().apply {
            title = "ซื้อกองทุนรวม"
            feel = "Unhappy"
            dayLeft = 20
            currentPrice = 500
            totalPrice = 6000
        }
        val obj3 = GoalModel().apply {
            title = "ไปทะเล"
            feel = "Good"
            dayLeft = 45
            currentPrice = 16500
            totalPrice = 20000
        }
        arrListGoal.add(obj1)
        arrListGoal.add(obj2)
        arrListGoal.add(obj3)

        rvGoalAdapter?.setDataItem(arrListGoal)
    }

    override fun apiListGoalSuccess() {

    }

    override fun apiListGoalFail() {

    }

    override fun apiListOffer() {//type offer
        val arrData = ArrayList<String>()
        arrData.add("")
        arrData.add("")
        arrData.add("")
        arrData.add("")
        rvOfferAdapter?.setDataItem(arrData)
    }

    override fun apiListOfferSuccess() {

    }

    override fun apiListOfferFail() {

    }

    override fun apiListSuggest() {//type suggest
        val arrData = ArrayList<String>()
        arrData.add("")
        arrData.add("")
        arrData.add("")
        rvSuggestAdapter?.setDataItem(arrData)
    }

    override fun apiListSuggestSuccess() {

    }

    override fun apiListSuggestFail() {

    }

    override fun goToNewGoal() {

    }

}