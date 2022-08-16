package com.example.wordsapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.R
import com.example.wordsapp.adapter.LetterAdapter
import com.example.wordsapp.databinding.FragmentLetterListBinding


class LetterListFragment : Fragment() {

    // binding object - getting a reference to the FragmentLetterListBinding
    private var _binding : FragmentLetterListBinding? = null // not accessed directly

    // binding object
    private val binding get() = _binding!! // only binding gets the value of _binding

    // RecyclerView
    private lateinit var recyclerView: RecyclerView

    // to keep track of which layout state
    private var isLinearLayoutManager = true

    // Fragment Lifecycle Function
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    // Fragment Lifecycle Function -  bind specific views to properties by calling findViewById() or binding object.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // change different layout managers based on user clicks
    private fun chooseLayout(){
        if(isLinearLayoutManager){

            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    // change menu item icon based on user clicks
    private fun setIcon(menuItem: MenuItem?){
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
            else
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)

    }

    // Create option menu cmd + o , ctrl + o
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // inflate menu layout
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    // option menu item selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // return an action, behaviour
        return when(item.itemId){
            R.id.action_switch_layout ->
            {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooseLayout()
                setIcon(item)

                return true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

}