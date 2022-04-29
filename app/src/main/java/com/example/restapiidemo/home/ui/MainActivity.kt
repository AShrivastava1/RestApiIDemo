package com.example.restapiidemo.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.restapiidemo.home.data.PostModel
import com.example.restapiidemo.home.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var vm: HomeViewModel
    private lateinit var orderItems: List<PostModel>
    private lateinit var orderItem: PostModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProvider(this)[HomeViewModel::class.java]

        vm.fetchAllPosts()

        vm.postModelListLiveData?.observe(this, Observer {
            orderItems = it
            setContent {
                MyApp {
                    orderItem = it
                    startActivity(OrderDetailsMain.newIntent(this,orderItem))
                }
            }
        })

    }

    @Composable
    fun MyApp(navigateToProfile: (PostModel) -> Unit) {
        Scaffold(
            content = {
                OrderDetailsContent(navigateToProfile = navigateToProfile, orderItem = orderItems)
            }
        )
    }
}





