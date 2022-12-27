package com.example.kotlinhw3.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinhw3.StatusLoad
import com.example.kotlinhw3.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.setProfile(2)

        viewModel.profile.observe(viewLifecycleOwner) {
            binding.fragmentProfileUsername.setText(it.user.username)
            binding.fragmentProfileNickname.setText(it.nickname)
        }

        viewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                StatusLoad.SUCCESS -> {}
                StatusLoad.LOADING -> {}
                StatusLoad.ERROR -> {
                    Log.d("HW3:PROFILE", "Error")
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