package com.example.kotlinhw3.ui.question_with_answers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinhw3.R
import com.example.kotlinhw3.StatusLoad
import com.example.kotlinhw3.databinding.FragmentQuestionWithAnswersBinding
import com.example.kotlinhw3.models.QuestionsResponse
import com.example.kotlinhw3.ui.adapters.RVAnswersListAdapter
import com.example.kotlinhw3.ui.adapters.RVQuestionsListAdapter

class QWAsFragment(
    private val question: QuestionsResponse.Item
) : Fragment() {

    private var _binding: FragmentQuestionWithAnswersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this).get(QWAsViewModel::class.java)

        _binding = FragmentQuestionWithAnswersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.fragmentQWAsQuestionItem.apply {
            questionItemTitle.text = question.title
            questionItemText.text = question.text
            questionItemTags.text = "Tags:"
            questionItemLikeNumber.text = "0"
        }

        binding.fragmentQWAsQuestionItem.questionItemCard.setOnClickListener{}
        binding.fragmentQWAsQuestionItem.questionItemLike.apply {
            setOnClickListener {
                setColorFilter(requireContext().applicationContext.getColor(R.color.purple_200))
                binding.fragmentQWAsQuestionItem.questionItemLikeNumber.apply {
                    text = (text.toString().toInt() + 1).toString()
                }
            }
        }

        val rvAdapter = RVAnswersListAdapter()

        binding.fragmentQWAsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }

        Log.d("TESTNEW", question.id.toString())

        viewModel.addItems(question.id)

        viewModel.answers.observe(viewLifecycleOwner){
            if (it != null && viewModel.status.value == StatusLoad.SUCCESS)
                rvAdapter.refreshItems(it)
        }

        viewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                StatusLoad.SUCCESS -> {}
                StatusLoad.LOADING -> {}
                StatusLoad.ERROR -> {
                    Log.d("HW3:ANSWERS", "Error")
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