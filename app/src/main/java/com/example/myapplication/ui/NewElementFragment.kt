package com.example.myapplication.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.ElementApplication
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNewElementBinding
import com.example.myapplication.room.EntityElements
import com.example.myapplication.viewmodel.MainViewModel
import com.example.myapplication.viewmodel.ViewModelFactory

/* In the fragment new element
new items are inserted into the database list
 */

class NewElementFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels { ViewModelFactory((activity?.applicationContext as ElementApplication).repository) }

    private var _binding: FragmentNewElementBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewElementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.button.setOnClickListener { insert() }
    }

    private fun insert() {
        val hideKeyboard = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val text = binding.newElement.text.toString()
        if (TextUtils.isEmpty(text)) {
            return
        }else {
            val textNewElement = "- $text"
            val element = EntityElements(textNewElement)
            mainViewModel.insertElement(element)
            findNavController().navigate(R.id.action_newElementFragment_to_mainFragment)
        }
        // hide keyboard
        hideKeyboard.hideSoftInputFromWindow( binding.newElement.windowToken , 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}