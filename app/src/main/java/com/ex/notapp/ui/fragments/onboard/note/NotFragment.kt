package com.ex.notapp.ui.fragments.onboard.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ex.notapp.R
import com.ex.notapp.databinding.FragmentNotBinding
import com.ex.notapp.utills.PreferenceHelper


class NotFragment : Fragment() {

    private lateinit var binding: FragmentNotBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onStart() {
        super.onStart()


    }





}