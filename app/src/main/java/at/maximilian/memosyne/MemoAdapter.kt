package at.maximilian.memosyne

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.maximilian.memosyne.databinding.MemoRowItemBinding
import at.maximilian.memosyne.db.Memo

class MemoAdapter() :
    ListAdapter<Memo, RecyclerView.ViewHolder>(MemoDiffCallback()) {

    class MemoViewHolder(private val binding: MemoRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            // TODO: Implement members
        }

        fun bind(item: Memo) {
            binding.apply {
                textViewMemoTitle.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view =
            MemoRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val memo = getItem(position)
        (holder as MemoViewHolder).bind(memo)
    }
}

private class MemoDiffCallback : DiffUtil.ItemCallback<Memo>() {
    override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
        return oldItem == newItem
    }

}
