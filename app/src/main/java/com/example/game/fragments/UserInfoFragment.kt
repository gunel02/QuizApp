package com.example.game.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.IOException
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.game.adapter.UserInfoRecycleAdapter
import com.example.game.data_classes.user_info.UserInfoModel
import com.example.game.databinding.FragmentUserInfoBinding
import com.example.game.objects.UserInfoRetrofit
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class UserInfoFragment : Fragment() {
    private lateinit var binding: FragmentUserInfoBinding
    private var infoList: UserInfoModel? = null


    @SuppressLint("CheckResult")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserInfoBinding.inflate(inflater, container, false)

        initListener()


        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                UserInfoRetrofit.api.getUsersInfo()
            } catch (e: IOException) {
                Toast.makeText(requireContext(), "app error ${e.message}", Toast.LENGTH_SHORT)
                    .show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(requireContext(), "http error ${e.message}", Toast.LENGTH_SHORT)
                    .show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    infoList = response.body()
                    binding.userInfoRecycler.adapter =
                        UserInfoRecycleAdapter(context, infoList?.data ?: emptyList())
                    binding.userInfoRecycler.layoutManager =
                        LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                }
            }
        }

        return binding.root
    }

    private fun initListener() {
        binding.backButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()

        }
    }
}