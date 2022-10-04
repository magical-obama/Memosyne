package at.maximilian.memosyne

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.maximilian.memosyne.databinding.MemoRowItemBinding
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.viewmodels.OverviewViewModel

/**
 * Adapter for the [RecyclerView], derived from the [ListAdapter]
 */
class MemoAdapter(
    private val viewModel: OverviewViewModel,
    private val navController: NavController
) :
    ListAdapter<Memo, RecyclerView.ViewHolder>(MemoDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(item: Memo)
    }

    class MemoViewHolder(private val binding: MemoRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Memo, viewModel: OverviewViewModel, navController: NavController) {
            binding.apply {
                textViewMemoTitle.text = item.title
            }
            itemView.findViewById<ImageButton>(R.id.imageButton_removeMemo).setOnClickListener {
                viewModel.deleteMemo(item)
            }

            itemView.setOnClickListener {
                navController.navigate(R.id.action_OverviewFragment_to_AddMemoFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = MemoRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val memo = getItem(position)
        (holder as MemoViewHolder).bind(memo, viewModel, navController)
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