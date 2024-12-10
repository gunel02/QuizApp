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
import com.example.game.adapter.PersonRecycleAdapter
import com.example.game.data_classes.user_info.UserData
import com.example.game.databinding.FragmentPersonBinding
import com.example.game.objects.RetrofitInstance
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class PersonFragment : Fragment() {

    private lateinit var binding: FragmentPersonBinding
    private lateinit var userRecycleAdapter: PersonRecycleAdapter
    private lateinit var userList: List<UserData>


    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonBinding.inflate(inflater, container, false)

        initListener()

        //@Get from service
        userList = listOf()

        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getAllUsers()
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
                    userList = response.body()!!
                    binding.recycleTest.apply {
                        userRecycleAdapter = PersonRecycleAdapter(userList)
                        adapter = userRecycleAdapter
                        layoutManager =
                            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    }
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







