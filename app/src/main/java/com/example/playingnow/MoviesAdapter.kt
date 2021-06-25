package com.example.playingnow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.playingnow.modelos.ResultMovie

class MoviesAdapter(private val images:List<String>, private val details: List<ResultMovie>):RecyclerView.Adapter<MovieViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        println(details)
        var item: String = images[position]

        holder.bind(item, details)
    }
}