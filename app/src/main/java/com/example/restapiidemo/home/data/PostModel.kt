package com.example.restapiidemo.home.data

import java.io.Serializable

data class PostModel(
    var userId:Int?=0,
    var id:Int?=0,
    var title:String="",
    var category:String="",
    var price:String="",
    var image:String="",
    var description:String="",
    var body:String="",
    var size:String="XXXX",
    var status:String="Order Confirmed",
    var date : String ="29th Apr 2022"
) : Serializable