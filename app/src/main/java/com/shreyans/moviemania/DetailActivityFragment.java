package com.shreyans.moviemania;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        //ImageView poster = (ImageView) rootView.findViewById(R.id.movie_poster);

        String baseUrl= "http://image.tmdb.org/t/p/";
        /* Possible Image sizes are "w92", "w154", "w185", "w342", "w500", "w780", or "original" */

        String size="w500/";// for BackDrop Image
        //Picasso.with(mContex).load(baseUrl+size+ movie.getBackdropPath()).into(poster);
        return rootView;
    }
}
