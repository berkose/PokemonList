package com.example.pokemonlist.Adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.pokemonlist.Common.Common
import com.example.pokemonlist.Interface.IItemClickListener
import com.example.pokemonlist.Model.Pokemon
import com.example.pokemonlist.R

class PokemonListAdapter (internal var context: Context,
                            internal var pokemonList: List<Pokemon>):RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(context).inflate(R.layout.pokemon_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(pokemonList[position].img).into(holder.img_pokemon)
        holder.txt_pokemon.text = pokemonList[position].name

        holder.setItemClickListener(object : IItemClickListener {
            override fun onClick(view: View, position: Int) {
                //Toast.makeText(context,"Clicked at Pokemon" + pokemonList[position], Toast.LENGTH_SHORT).show()
                LocalBroadcastManager.getInstance(context)
                    .sendBroadcast(Intent(Common.KEY_ENABLE_HOME).putExtra("position",position))
            }

        })
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var img_pokemon: ImageView
        internal var txt_pokemon: TextView

        internal var itemClickListener: IItemClickListener? = null

        fun setItemClickListener(iItemClickListener: IItemClickListener) {
            this.itemClickListener = iItemClickListener
        }

        init {
            img_pokemon = itemView.findViewById(R.id.pokemon_image) as ImageView
            txt_pokemon = itemView.findViewById(R.id.pokemon_name) as TextView

            itemView.setOnClickListener { view ->
                itemClickListener!!.onClick(view, adapterPosition)
            }
        }


    }
}