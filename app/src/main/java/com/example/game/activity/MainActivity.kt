package com.example.game.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.game.R
import com.example.game.databinding.ActivityMainBinding
import com.example.game.fragments.HomeFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        init()
        initListeners()



        setContentView(binding.root)

//        val retrofitService =
//            RetrofitInstance.getRetrofitInstance().create(InterfaceApi::class.java)
//
//        val responseLiveData: LiveData<Response<Albums>> = liveData {
//            val response = retrofitService.getAlbums()
//            emit(response)
//        }
//        responseLiveData.observe(this) {
//            val albumList = it.body()?.listIterator()
//            if (albumList != null) {
//                while (albumList.hasNext()) {
//                    val albumsItem = albumList.next()
//                    val albumTitle = "Album Title: ${albumsItem} "
//                    binding.text.append(albumTitle)
//                }
//            }
//        }

    }

    private fun initListeners() {

    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun init() {
        val fragment = HomeFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()


    }


}
