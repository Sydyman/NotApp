package com.ex.notapp.ui.fragments.onboard.note

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ex.notapp.R
import com.ex.notapp.R.color.black2
import com.ex.notapp.data.models.NoteModel
import com.ex.notapp.databinding.FragmentDetailBinding
import com.ex.notapp.utills.App
import java.util.Date
import java.util.Locale


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        getRealTime()
    }


    @SuppressLint("ResourceAsColor")
    private fun setUpListeners() {
        binding.txtReady.setOnClickListener {
            val etTitle = binding.etTitle.text.toString().trim()
            val etDescription = binding.etDescription.text.toString().trim()
            val drawable = binding.detailFragment.background
            val color = if (drawable is ColorDrawable) {
                drawable.color
            } else {
                Color.TRANSPARENT
            }
            val fragmentColor = String.format("#%06X", (0xFFFFFF and color))
            //код по большей части писал я сам, спасибо ютубу, минимум chatGpt)


            App.appDatabase?.noteDao()?.insert(NoteModel(etTitle, etDescription, fragmentColor))
            findNavController().navigateUp()
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.fon1.setOnClickListener {
            binding.detailFragment.setBackgroundColor(Color.parseColor("#0E0C0C"))
            binding.dot.translationX = 0f


        }
        binding.fon2.setOnClickListener {
            binding.detailFragment.setBackgroundColor(Color.parseColor("#EBE6E6"))
            binding.dot.translationX = 300f
        }
        binding.fon3.setOnClickListener {
            binding.detailFragment.setBackgroundColor(Color.parseColor("#571818"))
            binding.dot.translationX = 600f
        }
    }

    private fun getRealTime() {
        val dateFormat = SimpleDateFormat("day, week", Locale.getDefault())
        val timeFormat = SimpleDateFormat("H:m", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        val currentTime = timeFormat.format(Date())
        binding.txtTime.text = currentTime
        binding.txtDate.text = currentDate
    }


}