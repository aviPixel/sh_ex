package core.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ViewHolderEmpty(itemsView: View): RecyclerView.ViewHolder(itemsView) {
    fun bind() {
        itemView.apply {
        }
    }
}