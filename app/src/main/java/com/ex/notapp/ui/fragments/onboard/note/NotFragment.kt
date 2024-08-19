package com.ex.notapp.ui.fragments.onboard.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ex.notapp.R
import com.ex.notapp.adapter.NoteAdapter
import com.ex.notapp.databinding.FragmentNotBinding
import com.ex.notapp.utills.App
import com.ex.notapp.utills.PreferenceHelper


class NotFragment : Fragment() {

    private lateinit var binding: FragmentNotBinding
    private var noteAdapter = NoteAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        getData()
        setUpListeners()


    }


    private fun initialize() {
        binding.rvNote.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }


    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }

    }

    private fun setUpListeners() {
        binding.btnPlus.setOnClickListener {
            findNavController().navigate(R.id.action_notFragment_to_detailFragment)
        }
    }


}