package com.sh.ex.newgoal.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sh.ex.R
import kotlinx.android.synthetic.main.view_holder_new_goal.view.*

class ListNewGoalAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_ITEM = 1

    private lateinit var mContext: Context
    private lateinit var mListener: OnItemClickListener

    private lateinit var mListData: ArrayList<String>

    constructor(context: Context, listener: OnItemClickListener) : this() {
        this.mContext = context
        this.mListener = listener
        mListData = ArrayList()
    }

    interface OnItemClickListener {
        fun onItemClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_new_goal, parent, false))
    }

    override fun getItemCount(): Int {
        return mListData.size
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(mListData[position])
        }
    }

    fun setDataItem(listData: ArrayList<String>) {
        mListData = listData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        fun bind(data: String) {
            itemView.apply {
                tvTitle.text =  data
            }
        }
    }

}