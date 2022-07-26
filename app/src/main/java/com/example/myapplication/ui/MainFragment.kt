package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ElementApplication
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.recyclerview.AdapterRecyclerView
import com.example.myapplication.viewmodel.MainViewModel
import com.example.myapplication.viewmodel.ViewModelFactory

/* This is main fragment
In it, the list of elements available in the database appears through [MainViewModel]
and functionality is given to the [add] and [delete] elements buttons.
 */

class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels { ViewModelFactory((activity?.applicationContext as ElementApplication).repository) }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AdapterRecyclerView()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.viewModel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.add.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_newElementFragment) }
        binding.fabDelete.setOnClickListener { mainViewModel.deleteList()
        binding.fabDelete.visibility = View.INVISIBLE }

        mainViewModel.allElements.observe(viewLifecycleOwner) { element ->
            element?.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}