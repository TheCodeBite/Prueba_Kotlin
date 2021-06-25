package com.example.playingnow

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.playingnow.databinding.ItemMovieBinding
import com.example.playingnow.modelos.ResultMovie
import com.squareup.picasso.Picasso

class MovieViewHolder(view:View):RecyclerView.ViewHolder(view){

    private val binding = ItemMovieBinding.bind(view)

    fun bind(image: String, pelicula: List<ResultMovie>){
        Picasso.get().load(image).into(binding.ivMovie)
        binding.tvTitle.text = pelicula[0].title
        binding.tvDescription.text = pelicula[0].overview
    }
}