package at.maximilian.memosyne

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.maximilian.memosyne.databinding.MemoRowItemBinding
import at.maximilian.memosyne.db.Memo

/**
 * Adapter for the [RecyclerView], derived from the [ListAdapter]
 */
class MemoAdapter() :
    ListAdapter<Memo, RecyclerView.ViewHolder>(MemoDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(item: Memo)
    }

    class MemoViewHolder(private val binding: MemoRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Memo) {
            binding.apply {
                textViewMemoTitle.text = item.title
            }
            itemView.findViewById<ImageButton>(R.id.imageButton_removeMemo).setOnClickListener {
                Log.d("Delete Memo", "Deleting memo with id ${item.uid} and title ${item.title}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = MemoRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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