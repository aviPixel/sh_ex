package com.sh.ex.menuone.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sh.ex.R
import core.util.GlideLib
import core.util.ViewHolderEmpty
import kotlinx.android.synthetic.main.view_holder_offer.view.*

class ListOfferAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_EMPTY = 0
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
        return when (viewType) {
            VIEW_ITEM -> {
                ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_offer, parent, false))
            }
            else -> {
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

    fun setDataItem(listData: ArrayList<String>) {
        mListData = listData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        fun bind(data: String) {
            itemView.apply {
                GlideLib.setImage(mContext, R.drawable.img_test, ivItem)
            }
        }
    }

}