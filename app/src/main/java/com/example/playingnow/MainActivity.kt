package com.example.playingnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playingnow.databinding.ActivityMainBinding
import com.example.playingnow.interfaces.MovieService
import com.example.playingnow.modelos.PelicanModel
import com.example.playingnow.modelos.ResultMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private  lateinit var binding: ActivityMainBinding
    private  lateinit var adapterMovie: MoviesAdapter
    private val movieImages = mutableListOf<String>()
    private var movieDetails = mutableListOf<ResultMovie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svMovie.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapterMovie = MoviesAdapter(movieImages, movieDetails)
        binding.rvMovies.layoutManager = LinearLayoutManager(this )
        binding.rvMovies.adapter = adapterMovie
    }

    private fun getMovie():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchMovie(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val dataResponse = getMovie().create(MovieService::class.java).getAllMovies("movie/508943?api_key=66db8929cc45cabf3562b94573563ad5&language=es-ES")
            println(dataResponse)
            val movies = dataResponse.body()
            runOnUiThread {
                if(dataResponse.isSuccessful){
                    val images: ArrayList<String> = ArrayList()
                    images.add("https://static.wikia.nocookie.net/disney/images/c/cc/Luca_poster.jpg/revision/latest?cb=20210428133915")
                    movieImages.clear()
                    movieImages.addAll(images)
                    movieDetails.clear()
                    if (movies != null) {
                        movieDetails.add(movies)
                    }

                    println(movieDetails)
                    adapterMovie.notifyDataSetChanged()
                } else {
                    showError()
                }
            }

        }
    }

    private fun showError() {
        Toast.makeText(this, "Ah ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        println(query)
        if( !query.isNullOrEmpty() ) {
            searchMovie(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}