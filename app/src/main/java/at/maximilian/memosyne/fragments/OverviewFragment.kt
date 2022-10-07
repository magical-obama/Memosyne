package at.maximilian.memosyne.fragments

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.maximilian.memosyne.R
import at.maximilian.memosyne.databinding.FragmentOverviewBinding
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.viewmodels.OverviewViewModel
import at.maximilian.memosyne.viewmodels.SharedViewModel

/**
 * [Fragment] used for displaying all memo's currently stored
 */
class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val viewModel: OverviewViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

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
            sharedViewModel.select()
            findNavController().navigate(R.id.action_OverviewFragment_to_AddMemoFragment)
        }

        // Setup the recycler view
        val adapter = MemoAdapter(viewModel, sharedViewModel, findNavController())
        binding.recyclerViewMemos.adapter = adapter
        binding.recyclerViewMemos.layoutManager = LinearLayoutManager(context)
        subscribeToUiChanges(adapter)

        // Register menu items and handle menu click events
        setupMenu()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     *  Setup the menu items for this fragment and handle click events on the menu
     */
    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_overview, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_settings -> {
                        findNavController().navigate(R.id.action_OverviewFragment_to_settingsFragment)
                        return true
                    }
                    R.id.action_about_me -> {
                        findNavController().navigate(R.id.action_OverviewFragment_to_AboutMeFragment)
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