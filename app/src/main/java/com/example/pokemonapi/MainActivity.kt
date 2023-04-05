package com.example.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import java.util.*

class MainActivity : AppCompatActivity() {
    val rand = Random()
    var pokemon = rand.nextInt(1280)
    private lateinit var pokemonList: MutableList<String>
    private lateinit var rvPokemons: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPokemons = findViewById(R.id.pokemon_list)
        pokemonList = mutableListOf()
        getpokemonImageURL()
    }
    private fun getpokemonImageURL(){
        val client = AsyncHttpClient()
        pokemon = rand.nextInt(1280)
        Log.d("Test", pokemon.toString())
        for (i in 0 until 20) {
            pokemon = rand.nextInt(1280)
            client["https://pokeapi.co/api/v2/pokemon/$pokemon", object :
                JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                    var resultsJSON = json.jsonObject.getJSONObject("sprites")
                    pokemonList.add(resultsJSON.getString("front_default"))
                    pokemonList.add(json.jsonObject.getString("name"))
                    pokemonList.add(json.jsonObject.getString("weight"))
                    val adapter = PokemonAdapter(pokemonList)
                    rvPokemons.adapter = adapter
                    rvPokemons.layoutManager = LinearLayoutManager(this@MainActivity)

                    Log.d("done", pokemonList.size.toString())
                    //pokemonImageURL = resultsJSON.getString("front_default")
                    //pokeName = json.jsonObject.getString("name")
                    //pokeWeight = json.jsonObject.getString("weight")
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    throwable: Throwable?
                ) {
                    Log.d("Error", pokemon.toString())
                }
            }]
            Log.d("hi", pokemonList.size.toString())
        }
    }
}