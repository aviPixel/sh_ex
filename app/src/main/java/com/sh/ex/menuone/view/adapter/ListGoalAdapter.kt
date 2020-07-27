package com.sh.ex.menuone.view.adapter

import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sh.ex.R
import com.sh.ex.menuone.model.GoalModel
import core.util.*
import kotlinx.android.synthetic.main.view_holder_goal.view.*


class ListGoalAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_EMPTY = 0
    private val VIEW_ITEM = 1

    private lateinit var mContext: Context
    private lateinit var mListener: ListGoalAdapter.OnItemClickListener

    private lateinit var mListData: ArrayList<GoalModel>

    constructor(context: Context, listener: OnItemClickListener) : this() {
        this.mContext = context
        this.mListener = listener
        mListData = ArrayList()
    }

    interface OnItemClickListener {
        fun onItemClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_ITEM -> {
                ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_goal, parent, false))
            } else -> {
                ViewHolderEmpty(LayoutInflater.from(parent.context).inflate(R.layout.view_empty_start, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return mListData.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return VIEW_EMPTY
        }
        return VIEW_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(mListData[position - 1])
        } else if (holder is ViewHolderEmpty) {
            holder.bind()
        }
    }

    fun setDataItem(listData: ArrayList<GoalModel>) {
        mListData = listData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        fun bind(data: GoalModel) {
            itemView.apply {
                val params = MainRootView.layoutParams
                val paddingItem = mContext.convertDpToPx(24.toFloat())
                val widthDisplay = ((getWidthDisplay() - paddingItem) / 2.5).toInt()
                params.width = widthDisplay
                params.height = widthDisplay
                MainRootView.layoutParams = params

                frameItem.setBackgroundResource(R.drawable.bg_item_goal_green)
                tvFeel.setTextColor(ContextCompat.getColor(mContext, R.color.green))
                if (data.feel == "Unhappy") {
                    frameItem.setBackgroundResource(R.drawable.bg_item_goal_red)
                    tvFeel.setTextColor(ContextCompat.getColor(mContext, R.color.colorButtonNewGoal))
                }
                tvCurrentPrice.text = String.format(resources.getString(R.string.label_price_unit), data.currentPrice.toDouble().setNumberFormatDigit())
                tvTotalPrice.text = String.format(resources.getString(R.string.label_price_unit), data.totalPrice.toDouble().setNumberFormatDigit())
                tvTitle.text = data.title
                tvFeel.text = data.feel
                tvDayLeft.text = String.format(resources.getString(R.string.label_days_left), data.dayLeft.toString())

                /**
                 * status
                 */
                val sumTotal: Double = (data.currentPrice.toDouble() * 100.00) / data.totalPrice.toDouble()
                val paramsStatus = frameStatusCurrent.layoutParams
                paramsStatus.width = ((widthDisplay * sumTotal) / 100).toInt()
                frameStatusCurrent.layoutParams = paramsStatus
            }
        }
    }

}