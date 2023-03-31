package com.example.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import java.util.*

class MainActivity : AppCompatActivity() {
    var pokemonImageURL = ""
    var pokeName = ""
    var pokeWeight = ""
    val rand = Random()
    var pokemon = rand.nextInt(1280)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button: Button = findViewById(R.id.pokemonButton)
        var imageView: ImageView = findViewById(R.id.pokemonImage)
        var pokemonName: TextView = findViewById(R.id.pokemonName)
        var pokemonWeight: TextView = findViewById(R.id.pokemonWeight)
        getNextImage(button,imageView, pokemonName,pokemonWeight)
    }
    private fun getNextImage(button: Button, imageView: ImageView,pokemonName: TextView,pokemonWeight: TextView) {
        button.setOnClickListener {
            pokemon = rand.nextInt(1000)
            getpokemonImageURL()
            pokemonName.setText(pokeName)
            pokemonWeight.setText(pokeWeight)

            Glide.with(this)
                . load(pokemonImageURL)
                .fitCenter()
                .into(imageView)
        }
    }
    private fun getpokemonImageURL(){
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon/$pokemon", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                var resultsJSON = json.jsonObject.getJSONObject("sprites")
                pokemonImageURL = resultsJSON.getString("front_default")
                pokeName = json.jsonObject.getString("name")
                pokeWeight = json.jsonObject.getString("weight")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Error", errorResponse)
            }
        }]
    }
}