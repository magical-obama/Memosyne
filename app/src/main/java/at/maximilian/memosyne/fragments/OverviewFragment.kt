package at.maximilian.memosyne.fragments

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.maximilian.memosyne.MemoAdapter
import at.maximilian.memosyne.R
import at.maximilian.memosyne.databinding.FragmentOverviewBinding
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.viewmodels.OverviewViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * [Fragment] used for displaying all memo's currently stored
 */
class OverviewFragment : Fragment() {

    companion object {
        fun newInstance() = OverviewFragment()
    }

    private var _binding: FragmentOverviewBinding? = null
    private val viewModel: OverviewViewModel by viewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddMemo.setOnClickListener {
            findNavController().navigate(R.id.action_OverviewFragment_to_AddMemoFragment)
        }

        // Setup the recycler view
        val adapter = MemoAdapter()
        binding.recyclerViewMemos.adapter = adapter
        subscribeToUiChanges(adapter)
        binding.recyclerViewMemos.layoutManager = LinearLayoutManager(context)

        // Register menu items and handle menu click events
        setupMenu(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     *  Setup the menu items for this fragment and handle click events on the menu
     *  @param view The fragment's view
     */
    private fun setupMenu(view: View) {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_overview, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_settings -> {
                        Snackbar.make(view, "Not used", Snackbar.LENGTH_LONG).show()
                        return true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)
    }

    private fun subscribeToUiChanges(adapter: ListAdapter<Memo, RecyclerView.ViewHolder>) {
        viewModel.allMemos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}