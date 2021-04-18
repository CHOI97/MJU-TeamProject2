package com.example.mtmimyeon_gitmi.myclass

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mtmimyeon_gitmi.HomeActivity
import com.example.mtmimyeon_gitmi.databinding.FragmentMyclassMainBinding

class MyClassMainFragment private constructor() : Fragment() {
    private var _binding: FragmentMyclassMainBinding? = null

    // This property is only valid between onCreateView and OnDestroyView
    private val binding get() = _binding!!

    companion object {
        fun getInstance(): MyClassMainFragment {
            return MyClassMainFragment()
        }
    }

    // 뷰가 생성되었을 때, 프래그먼트와 레이아웃 연결
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentMyclassMainBinding.inflate(inflater, container, false)

        init()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun init() {
        binding.textViewMyclassMainTimetable.setOnClickListener {
            startActivity(Intent(context, MyClassTimeTableActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle())
        }

        binding.textViewMyclassMainProfessorToMail.setOnClickListener {
            startActivity(Intent(context, MyClassMailToProfessorActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle())
        }

        binding.textViewMyclassMainSubjectList.setOnClickListener {
            startActivity(Intent(context, MyClassSubjectList::class.java), ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle())
        }
    }
}