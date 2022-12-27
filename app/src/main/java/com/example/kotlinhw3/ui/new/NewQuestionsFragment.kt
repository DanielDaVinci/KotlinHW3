package com.example.kotlinhw3.ui.new

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinhw3.StatusLoad
import com.example.kotlinhw3.databinding.FragmentNewsBinding
import com.example.kotlinhw3.ui.adapters.RVQuestionsListAdapter

class NewQuestionsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this).get(NewQuestionsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvAdapter = RVQuestionsListAdapter()

        binding.fragmentNewsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }

        viewModel.addItems(10)

        viewModel.questions.observe(viewLifecycleOwner){
            if (it != null && viewModel.status.value == StatusLoad.SUCCESS)
                rvAdapter.refreshItems(it)
        }

        viewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                StatusLoad.SUCCESS -> {}
                StatusLoad.LOADING -> {}
                StatusLoad.ERROR -> {
                    Log.d("HW3:QUESTIONS:NEW", "Error")
                    Toast.makeText(context, "Internet: error", Toast.LENGTH_LONG).show()
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}