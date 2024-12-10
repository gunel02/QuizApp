package com.example.game.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.game.R
import com.example.game.buttomSheet.BottomSheetFragment
import com.example.game.buttomSheet.SecondLevelBottomSheet
import com.example.game.buttomSheet.ThirdShuffleBottomSheet
import com.example.game.databinding.FragmentHomeBinding
import com.example.game.objects.Const
import com.example.game.objects.SharedPreference
import java.util.Locale


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bottomSheetFragment: BottomSheetFragment
    private lateinit var secondLevelBottomSheetFragment: SecondLevelBottomSheet
    private lateinit var thirdLevelBottomSheetFragment: ThirdShuffleBottomSheet


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        loadLocale()

        initListener()

        return binding.root
    }


    private fun initListener() {

        binding.firstLevel.setOnClickListener {
            bottomSheetFragment = BottomSheetFragment()
            activity?.supportFragmentManager?.let { notNullActivity ->
                bottomSheetFragment.show(

                    notNullActivity, "bottomDialog"
                )
            }
        }
        binding.secondLevel.setOnClickListener {
            secondLevelBottomSheetFragment = SecondLevelBottomSheet()
            activity?.supportFragmentManager?.let { notNullActivity ->
                secondLevelBottomSheetFragment.show(
                    notNullActivity, "bottomDialog"
                )
            }
        }

        binding.thirdLevel.setOnClickListener {
            thirdLevelBottomSheetFragment = ThirdShuffleBottomSheet()
            activity?.supportFragmentManager?.let { notNullActivity ->
                thirdLevelBottomSheetFragment.show(
                    notNullActivity, "bottomDialog"
                )
            }
        }

        binding.changeLanguageButton.setOnClickListener {
            changeLanguageDialog()
        }
//
//        binding.usersButton.setOnClickListener{
//            val fragment = PersonFragment()
//            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
//            transaction.replace(R.id.fragment_container, fragment).addToBackStack(null)
//            transaction.commit()
//        }
//        binding.usersInfoButton.setOnClickListener{
//            val fragment = UserInfoFragment()
//            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
//            transaction.replace(R.id.fragment_container, fragment).addToBackStack(null)
//            transaction.commit()
//        }
//
//        binding.postButton.setOnClickListener{
//            val fragment = PostFragment()
//            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
//            transaction.replace(R.id.fragment_container, fragment).addToBackStack(null)
//            transaction.commit()
//        }
    }


    private fun setLocale(language: String) {
        val local = Locale(language)
        Locale.setDefault(local)
        val config = Configuration()
        config.locale = local
        val baseContext = requireContext()
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        SharedPreference.
        setLang(language)
    }
    
    private fun loadLocale() {
        setLocale(SharedPreference.getLang() ?: Const.ENGLISH_LANG)
    }

    private fun changeLanguageDialog() {
        val dialogView = layoutInflater.inflate(R.layout.layout_change_language, null)
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setView(dialogView)

        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.language_radio_group)
        val btnCancel = dialogView.findViewById<TextView>(R.id.button_cancel)

        when (SharedPreference.getLang()) {
            Const.ENGLISH_LANG -> radioGroup.check(R.id.radio_button_english)
            Const.RUSSIAN_LANG -> radioGroup.check(R.id.radio_button_russian)
            Const.TURKMEN_LANG -> radioGroup.check(R.id.radio_button_turkmen)
        }


        val nDialog = alertDialog.create()
        nDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        nDialog.show()


        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_button_english -> {
                    setLocale(Const.ENGLISH_LANG)
                    saveLanguagePreference(Const.ENGLISH_LANG)
                    setTexts()
                }

                R.id.radio_button_russian -> {
                    setLocale(Const.RUSSIAN_LANG)
                    saveLanguagePreference(Const.RUSSIAN_LANG)
                    setTexts()
                }

                R.id.radio_button_turkmen -> {
                    setLocale(Const.TURKMEN_LANG)
                    saveLanguagePreference(Const.TURKMEN_LANG)
                    setTexts()

                }
            }
            nDialog.dismiss()
        }

        btnCancel.setOnClickListener {
            nDialog.dismiss()
        }
    }

    private fun saveLanguagePreference(language: String) {
        SharedPreference.setLang(language)
    }

    private fun setTexts() {
        binding.firstLevelText.text = getString(R.string.level_1)
        binding.secondLevelText.text = getString(R.string.level_2)
        binding.thirdLevelText.text = getString(R.string.level_3)
        binding.chooseLevelText.text = getString(R.string.choose_your_level)
        binding.changeLanguageText.text = getString(R.string.change_language)
        binding.usersText.text = getString(R.string.users)
        binding.usersInfoText.text = getString(R.string.users_info)
    }
}