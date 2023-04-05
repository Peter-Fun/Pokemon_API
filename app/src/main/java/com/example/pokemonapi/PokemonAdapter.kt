package com.example.pokemonapi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter (private val pokemonList: List<String>): RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokemonImage: ImageView
        val pokemonName: TextView
        val pokemonWeight: TextView
        init {
            // Find our RecyclerView item's ImageView for future use
            pokemonImage = view.findViewById(R.id.pokemon_image)
            pokemonName = view.findViewById(R.id.pokemon_name)
            pokemonWeight = view.findViewById(R.id.pokemon_weight)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(pokemonList[position*3])
            .centerCrop()
            .into(holder.pokemonImage)
        holder.pokemonName.setText(pokemonList[position*3+1])
        holder.pokemonWeight.setText(pokemonList[position*3+2])
    }

    override fun getItemCount() = pokemonList.size
}