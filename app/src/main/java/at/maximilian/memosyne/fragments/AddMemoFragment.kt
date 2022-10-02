package at.maximilian.memosyne.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import at.maximilian.memosyne.App
import at.maximilian.memosyne.R
import at.maximilian.memosyne.db.AppDatabase
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.db.MemoRepository
import at.maximilian.memosyne.viewmodels.AddMemoViewModel
import com.google.android.material.snackbar.Snackbar

class AddMemoFragment : Fragment() {

    companion object {
        fun newInstance() = AddMemoFragment()
    }

    private val viewModel: AddMemoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_memo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Recover from ViewModel
        view.findViewById<EditText>(R.id.editText_memoTitle).setText(viewModel.title)
        view.findViewById<EditText>(R.id.editText_memoContent).setText(viewModel.content)

        view.findViewById<Button>(R.id.btn_add_new_memo).setOnClickListener {
            val memo =
                Memo(
                    title = view.findViewById<EditText>(R.id.editText_memoTitle).text.toString(),
                    content = view.findViewById<EditText>(R.id.editText_memoContent).text.toString()
                )
            if (memo.title.isBlank()) {
                Snackbar.make(view, "You need to give the memo a title", Snackbar.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val db = App().database
            MemoRepository(db.memoDao()).insertMemo(memo)
            findNavController().navigateUp()
        }
    }
}