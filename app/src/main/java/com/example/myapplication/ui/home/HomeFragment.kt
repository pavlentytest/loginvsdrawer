package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var sharedViewModel: HomeViewModel
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        sharedViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        sharedViewModel.text.observe(viewLifecycleOwner) {
            if(it == "success") {
                // выставляем для drawer режим - Unlocked
                requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
                    .setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                // показываем toolbar
                requireActivity().findViewById<Toolbar>(R.id.toolbar).setVisibility(View.VISIBLE)
            }
        }
        binding!!.button.setOnClickListener {
            sharedViewModel.setStatus()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}