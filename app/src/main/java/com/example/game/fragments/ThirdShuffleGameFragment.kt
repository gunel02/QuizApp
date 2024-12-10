package com.example.game.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.game.R
import com.example.game.databinding.FragmentThirdShuffleGameBinding
import kotlin.random.Random

class ThirdShuffleGameFragment : Fragment() {

    private lateinit var binding: FragmentThirdShuffleGameBinding
    private var firstNumber = -1
    private var secondNumber = -1
    private var correctAnswers = 0
    private var wrongAnswers = 0
    private var totalAnswers = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentThirdShuffleGameBinding.inflate(inflater, container, false)

        initListener()
        setRandomNumbers()

        return binding.root
    }


    private fun initListener() {
        binding.backButton.setOnClickListener {
            val exitDialog = Dialog(this.requireContext())
            exitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            exitDialog.setCancelable(false)
            exitDialog.setContentView(R.layout.layout_exit_dialog)
            exitDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            exitDialog.show()

            val yesButton: TextView = exitDialog.findViewById(R.id.yes_button)
            yesButton.setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
                exitDialog.dismiss()
            }

            val noButton: TextView = exitDialog.findViewById(R.id.no_button)
            noButton.setOnClickListener {
                exitDialog.dismiss()
            }

            val closeButton: ImageView = exitDialog.findViewById(R.id.close_button)
            closeButton.setOnClickListener {
                exitDialog.dismiss()
            }
        }

        binding.randomLeftButton.setOnClickListener {
            if (totalAnswers <= 14) {
                if (firstNumber > secondNumber) {
                    setResult(true)
                } else {
                    setResult(false)
                }
            }
        }

        binding.randomRightButton.setOnClickListener {
            if (totalAnswers <= 14) {
                if (firstNumber > secondNumber) {
                    setResult(true)
                } else {
                    setResult(false)
                }
            }

        }

    }

    private fun getRandomNumbers(): Int {
        return Random.nextInt(-50, -1)

    }

    private fun setRandomNumbers() {
        firstNumber = getRandomNumbers()
        secondNumber = getRandomNumbers()

        while (firstNumber == secondNumber) {
            secondNumber = getRandomNumbers()
        }

        binding.randomLeftButton.text = firstNumber.toString()
        binding.randomRightButton.text = secondNumber.toString()
    }

    private fun setResult(isCorrect: Boolean) {
        if (isCorrect) {
            correctAnswers++
            setBackgroundColor(R.color.green)
        } else {
            wrongAnswers++
            setBackgroundColor(R.color.red)
        }
        totalAnswers++

        if (totalAnswers > 14) {
            Toast.makeText(requireContext(), "Game Over", Toast.LENGTH_SHORT).show()
            showCustomDialogBox()
        } else {
            setRandomNumbers()
        }

    }

    private fun setBackgroundColor(background: Int) {
        when (totalAnswers) {
            1 -> binding.button1.setBackgroundResource(background)
            2 -> binding.button2.setBackgroundResource(background)
            3 -> binding.button3.setBackgroundResource(background)
            4 -> binding.button4.setBackgroundResource(background)
            5 -> binding.button5.setBackgroundResource(background)
            6 -> binding.button6.setBackgroundResource(background)
            7 -> binding.button7.setBackgroundResource(background)
            8 -> binding.button8.setBackgroundResource(background)
            9 -> binding.button9.setBackgroundResource(background)
            10 -> binding.button10.setBackgroundResource(background)
            11 -> binding.button11.setBackgroundResource(background)
            12 -> binding.button12.setBackgroundResource(background)
            13 -> binding.button13.setBackgroundResource(background)
            14 -> binding.button14.setBackgroundResource(background)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showCustomDialogBox() {

        val dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        val correctText: TextView = dialog.findViewById(R.id.correct_button)
        val wrongText: TextView = dialog.findViewById(R.id.wrong_button)


        correctText.text = getString(R.string.correct_answer, correctAnswers)
        wrongText.text = getString(R.string.wrong_answer, wrongAnswers)



        if (correctAnswers > wrongAnswers) {
            val tryAgain: TextView = dialog.findViewById(R.id.try_again_button)
            tryAgain.visibility = View.GONE
        } else {
            val nextButton: TextView = dialog.findViewById(R.id.next_button)
            nextButton.visibility = View.GONE
        }


        val tryAgain: TextView = dialog.findViewById(R.id.try_again_button)
        tryAgain.setOnClickListener {
            clearAllDataAndTryAgain()
            dialog.dismiss()
        }


        val nextButton: TextView = dialog.findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
            dialog.dismiss()

        }


        val exit: ImageView = dialog.findViewById(R.id.exit_button)
        exit.setOnClickListener {
            dialog.dismiss()
        }

    }


    private fun clearAllDataAndTryAgain() {
        val background = android.R.color.transparent
        binding.button1.setBackgroundResource(background)
        binding.button2.setBackgroundResource(background)
        binding.button3.setBackgroundResource(background)
        binding.button4.setBackgroundResource(background)
        binding.button5.setBackgroundResource(background)
        binding.button6.setBackgroundResource(background)
        binding.button7.setBackgroundResource(background)
        binding.button8.setBackgroundResource(background)
        binding.button9.setBackgroundResource(background)
        binding.button10.setBackgroundResource(background)
        binding.button11.setBackgroundResource(background)
        binding.button12.setBackgroundResource(background)
        binding.button13.setBackgroundResource(background)
        binding.button14.setBackgroundResource(background)
        correctAnswers = 0
        wrongAnswers = 0
        totalAnswers = 1
        setRandomNumbers()
    }

}