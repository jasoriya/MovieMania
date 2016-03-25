package com.shreyans.moviemania.API;

import com.shreyans.moviemania.model.MovieResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Shreyans on 22-01-2016.
 * Interface @MovieApiService & method @fetchMovies created to request a list of movies using the queries @apiKey & @sortBy */
public interface MovieApiService {
    @GET("discover/movie?vote_count.gte=250")
    Call<MovieResults> fetchMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortBy);
}