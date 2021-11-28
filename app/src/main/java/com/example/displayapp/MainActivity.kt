package com.example.displayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {

    var title: TextView? = null
    var rating: TextView? = null
    var duration: TextView? = null
    var price: TextView? = null

    var backgroundImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewsAndWidgets()
        restApi()

    }

    private fun initViewsAndWidgets() {
        title = findViewById(R.id.textView_title)
        rating = findViewById(R.id.textView_rating)
        duration = findViewById(R.id.textView_duration)
        price = findViewById(R.id.textView_price)
        backgroundImage = findViewById(R.id.imageView)
    }

    fun restApi() {


        val httpAsync = "https://create.cliomuseapp.com/tour.json"
            .httpGet()
            .responseObject(Tour.Deserializer()) { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        result.getException()
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Picasso.get().load(data.header_image).into(backgroundImage)
                        changeText (title, data.title)
                        changeText (rating, data.average_rating+"/5 "+"("+data.rating_count.toString()+")")
                        changeText(duration, "Duration: "+data.duration+" minutes")
                        changeText(price, data.retail_price+"€")

                    }
                }
            }
    }


    fun changeText (textView: TextView?, data: String?) {

        textView !!.text = data

    }


}


