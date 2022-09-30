package at.maximilian.memosyne.fragments

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.maximilian.memosyne.App
import at.maximilian.memosyne.MemoAdapter
import at.maximilian.memosyne.R
import at.maximilian.memosyne.databinding.FragmentOverviewBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        setupMenu(binding.root)
        val view = binding.root
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_memos)
        val adapter = MemoAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(App().applicationContext)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddMemo.setOnClickListener {
            findNavController().navigate(R.id.action_OverviewFragment_to_AddMemoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     *  Setup the menu items for this fragment
     *  @param view The fragment's view
     *  @return [Unit]
     */
    private fun setupMenu(view: View) {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
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
        })
    }
}