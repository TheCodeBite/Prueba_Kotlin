package com.example.playingnow.interfaces

import com.example.playingnow.modelos.ResultMovie
import retrofit2.Response

import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MovieService {
    @GET
    suspend fun getAllMovies(@Url url:String): Response<ResultMovie>
}


