package com.example.displayapp

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

class Tour (
    var sku:String?="",
    var title:String?="",
    var header_image:String?="",
    var thumbnail:String?="",
    var permalink:String?="",
    var average_rating:String?="",
    var rating_count:Int?=0,
    var retail_price:String?="",
    var sales_price:String?="",
    var duration:String?="",
){
    class Deserializer: ResponseDeserializable<Tour> {
        override fun deserialize(content: String): Tour? = Gson().fromJson(content, Tour::class.java)
    }
}