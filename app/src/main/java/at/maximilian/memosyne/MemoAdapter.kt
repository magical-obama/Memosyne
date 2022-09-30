package at.maximilian.memosyne

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import at.maximilian.memosyne.db.Memo

class MemoAdapter(private val dataSet: List<Memo>) :
    RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView

        init {
            // TODO: Implement members
            textViewTitle = view.findViewById(R.id.textView_memoTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.memo_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewTitle.text = dataSet[position].title
    }

    override fun getItemCount() = dataSet.size
}