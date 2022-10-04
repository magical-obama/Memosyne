package at.maximilian.memosyne

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.maximilian.memosyne.databinding.MemoRowItemBinding
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.viewmodels.OverviewViewModel

/**
 * Adapter for the [RecyclerView], derived from the [ListAdapter]
 */
class MemoAdapter(private val viewModel: OverviewViewModel) :
    ListAdapter<Memo, RecyclerView.ViewHolder>(MemoDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(item: Memo)
    }

    class MemoViewHolder(private val binding: MemoRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Memo, viewModel: OverviewViewModel) {
            binding.apply {
                textViewMemoTitle.text = item.title
            }
            itemView.findViewById<ImageButton>(R.id.imageButton_removeMemo).setOnClickListener {
                viewModel.deleteMemo(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = MemoRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val memo = getItem(position)
        (holder as MemoViewHolder).bind(memo, viewModel)
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