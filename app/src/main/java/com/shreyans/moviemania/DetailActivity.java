package com.shreyans.moviemania;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.shreyans.moviemania.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movie = (Movie) getIntent().getParcelableExtra(Intent.EXTRA_TEXT);
        Context mContex = getBaseContext();

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(movie.getTitle());

        ImageView backDropPoster = (ImageView) findViewById(R.id.movie_backdrop_poster);
        ImageView poster = (ImageView) findViewById(R.id.movie_poster);
        TextView movieTitle = (TextView) findViewById(R.id.movie_title);
        TextView movieReleaseDate = (TextView) findViewById(R.id.movie_release_date);
        TextView movieAvgRating = (TextView) findViewById(R.id.movie_avg_rating);
        TextView movieVoteCount = (TextView) findViewById(R.id.movie_vote_count);
        TextView movieSummary = (TextView) findViewById(R.id.movie_plot_summary);

        movieTitle.setText(movie.getTitle());
        movieReleaseDate.setText(movie.getReleaseDate());
        movieAvgRating.setText(Double.toString(movie.getVoteAverage()));
        movieVoteCount.setText("Rated by " + Integer.toString(movie.getVoteCount()) + " people");
        movieSummary.setText(movie.getOverview());

        String baseUrl= "http://image.tmdb.org/t/p/";
        /* Possible Image sizes are "w92", "w154", "w185", "w342", "w500", "w780", or "original" */

        String size="w500/";// for BackDrop Image
        Picasso.with(mContex).load(baseUrl+size+ movie.getBackdropPath()).into(backDropPoster);
        size="w342/";//for Thumbnail Image
        Picasso.with(mContex).load(baseUrl+size+ movie.getPosterPath()).into(poster);
    }

}
