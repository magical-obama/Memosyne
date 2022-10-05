package at.maximilian.memosyne.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import at.maximilian.memosyne.R
import at.maximilian.memosyne.databinding.FragmentAddMemoBinding
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.viewmodels.AddMemoViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * [Fragment] for creating a new Memo
 */
class AddMemoFragment : Fragment() {

    companion object {
        fun newInstance() = AddMemoFragment()
    }

    private var _binding: FragmentAddMemoBinding? = null
    private val viewModel: AddMemoViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val memoId = arguments?.getInt("memo_to_edit_id")
        var editMemo: Memo? = null
        if (memoId != null && memoId != -1) {
            editMemo = viewModel.getMemoById(memoId)
        }

        binding.editTextMemoTitle.setText(editMemo?.title ?: viewModel.title)
        binding.editTextMemoContent.setText(editMemo?.content ?: viewModel.content)

        view.findViewById<Button>(R.id.btn_add_new_memo).setOnClickListener {
            val memo = editMemo ?: Memo(
                uid = null,
                title = binding.editTextMemoTitle.text.toString(),
                content = binding.editTextMemoContent.text.toString()
            )
            if (memo.title.isBlank()) {
                Snackbar.make(view, "You need to give the memo a title", Snackbar.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            viewModel.insertMemo(memo)
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}