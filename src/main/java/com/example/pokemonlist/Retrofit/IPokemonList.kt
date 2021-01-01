package com.example.pokemonlist.Retrofit

import com.example.pokemonlist.Model.Pokedex
import io.reactivex.Observable
import retrofit2.http.GET

interface IPokemonList {
    @get:GET("pokedex.json")
    val listPokemon: Observable<Pokedex>
}