package com.example.game.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.game.adapter.PostRecyclerAdapter
import com.example.game.data_classes.user_info.post.PostRequest
import com.example.game.data_classes.user_info.post.UserResponse
import com.example.game.databinding.FragmentPostBinding
import com.example.game.objects.RetrofitInstance
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class PostFragment : Fragment() {

    lateinit var binding: FragmentPostBinding
    private lateinit var postRecyclerAdapter: PostRecyclerAdapter
    private lateinit var postList: List<UserResponse>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)

        postRequest()

        return binding.root
    }

    //Post from service (PersonFragmentPostUrl)
    @OptIn(DelicateCoroutinesApi::class)
    private fun postRequest() {
        postList = listOf()

        GlobalScope.launch(Dispatchers.IO) {
            val post = try {
                RetrofitInstance.api.postUser(
                    PostRequest("morpheus", "leader")
                )

            } catch (e: IOException) {
                Toast.makeText(requireContext(), "app error ${e.message}", Toast.LENGTH_SHORT)
                    .show()


                return@launch
            } catch (e: HttpException) {
                Toast.makeText(requireContext(), "http error ${e.message}", Toast.LENGTH_SHORT)
                    .show()
                return@launch
            }


            //you can see only in logcat(terminal)
            Log.e("TAG_yyyy", "onResponse" + post.code())
            Log.e("TAG_yyyy", "onResponseName" + post.body()?.name)
            Log.e("TAG_yyyy", "onResponseJob" + post.body()?.job)
            Log.e("TAG_yyyy", "onResponseCreatedAt" + post.body()?.createdAt)



            //post Response from service
            if (post.isSuccessful && post.body() != null) {
                withContext(Dispatchers.Main) {
                    postList = listOf(post.body()!!)
                    binding.postRecycle.apply {
                        postRecyclerAdapter = PostRecyclerAdapter(postList)
                        adapter = postRecyclerAdapter
                        layoutManager =
                            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    }
                }
            }

        }
    }
}