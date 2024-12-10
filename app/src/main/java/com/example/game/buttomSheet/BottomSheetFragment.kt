package com.example.game.buttomSheet

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.game.R
import com.example.game.databinding.FragmentBottomSheetBinding
import com.example.game.fragments.ShuffleGameFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentBottomSheetBinding.inflate(inflater,container,false)

        initListener()

        return binding.root
    }


    @SuppressLint("CommitTransaction")
    private fun initListener() {
        binding.startButton.setOnClickListener {

            val fragment = ShuffleGameFragment()
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
            transaction.replace(R.id.fragment_container, fragment).addToBackStack(null)
            transaction.commit()
            dismiss()

        }
    }
}