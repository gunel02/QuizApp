
package com.example.game.buttomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.game.R
import com.example.game.databinding.FragmentSecondLevelBottomSheetBinding
import com.example.game.fragments.SecondShuffleGameFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SecondLevelBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSecondLevelBottomSheetBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondLevelBottomSheetBinding.inflate(inflater, container, false)

        initListener()

        return binding.root
    }

    private fun initListener() {
        binding.clickStartButton.setOnClickListener{
            val fragment = SecondShuffleGameFragment()
            val transaction:FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
            transaction.replace(R.id.fragment_container, fragment).addToBackStack(null)
            transaction.commit()
            dismiss()
        }
    }

}