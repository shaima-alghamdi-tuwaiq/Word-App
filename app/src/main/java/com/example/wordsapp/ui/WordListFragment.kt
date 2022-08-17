package com.example.wordsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.R
import com.example.wordsapp.adapter.WordAdapter
import com.example.wordsapp.databinding.FragmentWordListBinding

class WordListFragment : Fragment() {



    // Companion object
    companion object {
        // Key
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    // binding object - getting a reference to the FragmentLetterListBinding
    private var _binding : FragmentWordListBinding? = null
    private val binding get() = _binding!!

    private lateinit var letterId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }

    // Fragment Lifecycle Function
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Fragment Lifecycle Function -  bind specific views to properties by calling findViewById() or binding object.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WordAdapter(letterId, requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    // Fragment Lifecycle Function
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}