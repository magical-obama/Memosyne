package at.maximilian.memosyne

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import at.maximilian.memosyne.db.AppDatabase
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.db.ioThread
import at.maximilian.memosyne.viewmodels.AddMemoViewModel

class AddMemoFragment : Fragment() {

    companion object {
        fun newInstance() = AddMemoFragment()
    }

    private lateinit var viewModel: AddMemoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_memo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Recover from ViewModel
        viewModel = ViewModelProvider(this)[AddMemoViewModel::class.java]
        view.findViewById<EditText>(R.id.editText_memoContent).setText(viewModel.content)

        view.findViewById<Button>(R.id.btn_add_new_memo).setOnClickListener {
            val db = AppDatabase.getInstance(view.context)
            val memo =
                Memo(content = view.findViewById<EditText>(R.id.editText_memoContent).text.toString())
            ioThread {
                db.memoDao().insertMemo(memo)
            }
            findNavController().navigateUp()
        }
    }
}