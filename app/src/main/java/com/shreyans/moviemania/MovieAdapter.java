package com.shreyans.moviemania;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.shreyans.moviemania.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shreyans on 03-02-2016.
 * The Adapter class for loading the data fetched by @onLoadMoviesEvent into
 * the grid view.
 */
public class MovieAdapter extends ArrayAdapter<Movie>{
    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    public MovieAdapter(Activity context, List<Movie> movieList) {
        super(context, 0, movieList);
    }

    @Override
    public View getView(int position, View recycledView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (recycledView == null){
            recycledView = LayoutInflater.from(getContext()).inflate(
            R.layout.grid_item_movies, parent, false);
        }
        ImageView imageView = (ImageView) recycledView.findViewById(R.id.grid_item_movies_image);
        imageView.setAdjustViewBounds(true);
        String baseUrl= "http://image.tmdb.org/t/p/";
        final String SIZE="w500/";
        Picasso.with(getContext())
                .load(baseUrl + SIZE + movie.getPosterPath())
                .into(imageView);
        return recycledView;
    }
}
