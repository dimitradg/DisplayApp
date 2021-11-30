package com.example.displayapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.squareup.picasso.Picasso
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    var title: TextView? = null
    var rating: TextView? = null
    var duration: TextView? = null
    var price: TextView? = null
    /*var languages: TextView? = null*/

    var header: ImageView? = null

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
        header = findViewById(R.id.imageView_header)
        /*languages = findViewById(R.id.textView_languages)*/
    }

    fun restApi() {


        "https://create.cliomuseapp.com/tour.json"
            .httpGet()
            .responseObject(Tour.Deserializer()) { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        result.getException()
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Picasso.get().load(data.header_image).into(header)
                        changeText (title, data.title)
                        changeText (rating, data.average_rating+"/5 "+"("+data.rating_count.toString()+")")
                        changeText(duration, "Duration: "+data.duration+" minutes")
                        changeText(price, data.retail_price+"€")

                        //changing substring colour

                        /*changeTextFont(rating, start: 7, end: rating.toString().length)
                        changeTextFont(duration, start: 1, end: 10)
                        changeTextFont(languages, start: 1, end: 15)*/

                    }
                }
            }
    }


    fun changeText (textView: TextView?, data: String?) {

        textView !!.text = data

    }

    /* function changing substring font
    fun changeTextFont(text: TextView, start: Int, end: Int)
    {
        val tSpannableString = SpannableString(text.toString())

        val gray = ForegroundColorSpan(Color.GRAY)

        tSpannableString.setSpan(gray, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        text !!.text = tSpannableString


    }*/


}


