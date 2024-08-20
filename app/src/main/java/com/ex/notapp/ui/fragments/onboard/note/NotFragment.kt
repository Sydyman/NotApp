package com.ex.notapp.ui.fragments.onboard.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ex.notapp.R
import com.ex.notapp.adapter.NoteAdapter
import com.ex.notapp.data.models.NoteModel
import com.ex.notapp.databinding.FragmentNotBinding
import com.ex.notapp.interfaces.OnClickItem
import com.ex.notapp.utills.App
import com.ex.notapp.utills.PreferenceHelper


class NotFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNotBinding
    private var noteAdapter = NoteAdapter(this, this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.changeForm.setOnClickListener {
            binding.rvNote.layoutManager = GridLayoutManager(context, 2)
            binding.changeForm.isClickable = false
            binding.changeForm.visibility = View.INVISIBLE
            binding.changeForm2.visibility = View.VISIBLE
        }
        binding.changeForm2.setOnClickListener {
            binding.rvNote.layoutManager = LinearLayoutManager(context)
            binding.changeForm.isClickable = true
            binding.changeForm.visibility = View.VISIBLE
            binding.changeForm2.visibility = View.INVISIBLE
        }
    }

    override fun onLongClickItem(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Удалить Запись?")
            setPositiveButton("Нет") { dialog, _ ->
                dialog.cancel()

            }
            setNegativeButton("да") { dialog, _ ->
                App.appDatabase?.noteDao()?.deleteNote(noteModel)

            }.show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NotFragmentDirections.actionNotFragmentToDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }


}