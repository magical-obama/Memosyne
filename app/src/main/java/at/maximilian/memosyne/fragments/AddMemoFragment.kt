package at.maximilian.memosyne.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import at.maximilian.memosyne.R
import at.maximilian.memosyne.databinding.FragmentAddMemoBinding
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.viewmodels.AddMemoViewModel
import at.maximilian.memosyne.viewmodels.SharedViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * [Fragment] for creating a new Memo
 */
class AddMemoFragment : Fragment() {

    private var _binding: FragmentAddMemoBinding? = null
    private val viewModel: AddMemoViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

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
        val isNewMemo = sharedViewModel.selected == null
        if (!isNewMemo) {
            val memo = sharedViewModel.selected!!.value!!
            binding.btnAddNewMemo.text = getString(R.string.edit_memo_button_text)
            binding.editTextMemoTitle.setText(memo.title)
            binding.editTextMemoContent.setText(memo.content)
        }

        binding.btnAddNewMemo.setOnClickListener {
            if (binding.editTextMemoTitle.text.toString().isBlank()) {
                Snackbar.make(
                    view,
                    getString(R.string.snackbar_title_needed),
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }
            val memo: Memo
            if (isNewMemo) {
                // Log.d("NewMemoOrNot", "This is a new Memo!")
                memo = Memo(
                    title = binding.editTextMemoTitle.text.toString(),
                    content = binding.editTextMemoContent.text.toString()
                )
            } else {
                memo = sharedViewModel.selected!!.value!!
                // Log.d("NewMemoOrNot", "This is a old Memo with title ${memo.title}!")
                memo.title = binding.editTextMemoTitle.text.toString()
                memo.content = binding.editTextMemoContent.text.toString()
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