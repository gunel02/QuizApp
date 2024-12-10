package com.example.game.buttomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.game.R
import com.example.game.databinding.FragmentThirdShuffleBottomSheetBinding
import com.example.game.fragments.ThirdShuffleGameFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ThirdShuffleBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentThirdShuffleBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdShuffleBottomSheetBinding.inflate(inflater, container, false)

        initListener()

        return binding.root
    }


    private fun initListener() {
        binding.startButton.setOnClickListener{
            val fragment = ThirdShuffleGameFragment()
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container,fragment).addToBackStack(tag)
            transaction.replace(R.id.fragment_container, fragment).addToBackStack(null)
            transaction.commit()
            dismiss()
        }
    }

}