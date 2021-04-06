package com.example.moviesflix;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public abstract interface MovieService {
    @GET("films")
    abstract Call<List<MovieResponse>> getAllMovies();
}
