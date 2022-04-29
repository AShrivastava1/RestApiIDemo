package com.example.restapiidemo.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.restapiidemo.home.data.PostModel


class OrderDetailsMain  : AppCompatActivity() {

    private val orderDetails: PostModel by lazy {
        intent?.getSerializableExtra(ORDER_ID) as PostModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                OrderScreen(orderDetails = orderDetails)
            }
    }

    companion object {
        private const val ORDER_ID = "order_id"
        fun newIntent(context: Context, orderDetails: PostModel) =
            Intent(context, OrderDetailsMain::class.java).apply {
                putExtra(ORDER_ID, orderDetails)
            }
    }
}