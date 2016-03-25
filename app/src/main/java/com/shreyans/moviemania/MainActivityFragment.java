package com.shreyans.moviemania;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.shreyans.moviemania.API.MovieApiService;
import com.shreyans.moviemania.model.Movie;
import com.shreyans.moviemania.model.MovieResults;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String LOG_TAG = "ServiceGenerator";
    private String SAVED_MOVIES_GRID = "saved_movies_grid";
    private String SAVED_SORT_PREF = "saved_sort_pref";
    public static final String API_BASE_URL = "http://api.themoviedb.org/3/";
    private String sortBy="popularity.desc"; //Default sorting order
    private ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
    private MovieAdapter movieAdapter;

    public MainActivityFragment() {
    }

        private void onLoadMoviesEvent() {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient httpClient = new OkHttpClient();
            httpClient.interceptors().add(logging);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();

            MovieApiService movieApiService = retrofit.create(MovieApiService.class);

            Call<MovieResults> call = movieApiService.fetchMovies(BuildConfig.MY_MOVIE_DB_API_KEY, sortBy);

            call.enqueue(new Callback<MovieResults>(){
                @Override
                public void onResponse(Response<MovieResults> response, Retrofit retrofit1) {
                    MovieResults movieResults = response.body();
                    if (movieResults != null) {
                        Log.i(LOG_TAG, "Got requested object: " + movieResults.getTotalPages() + " results.");

                        movieArrayList = (ArrayList<Movie>) movieResults.getResults();

                        Log.i(LOG_TAG, "Retrieved arraylist with 1st movie: \n" +
                                "Name: " + movieArrayList.get(0).getTitle()+
                                "\nPoster path: " + movieArrayList.get(0).getPosterPath() +
                                "\nRelease Date: " + movieArrayList.get(0).getReleaseDate() +
                                "\nOverview: " + movieArrayList.get(0).getOverview());
                        movieAdapter.clear();
                        movieAdapter.addAll(movieArrayList);
                        movieAdapter.notifyDataSetChanged();
                    }
                    else {
                        Log.e(LOG_TAG, "Getting null object of MovieResults");
                        try {
                            String string = response.errorBody().string();
                            Log.i(LOG_TAG, string);
                        }
                        catch (IOException e) {
                            Log.e(LOG_TAG, "IOException inside the response");
                        }
                    }

                }
                @Override
                public void onFailure(Throwable throwable) {
                    Log.i(LOG_TAG, "Retrofit response failure");
                }

            });
        }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (savedInstanceState == null || !savedInstanceState.containsKey(SAVED_MOVIES_GRID) || !savedInstanceState.containsKey(SAVED_SORT_PREF)){
            onLoadMoviesEvent();
        }
        else {
            movieArrayList = savedInstanceState.getParcelableArrayList(SAVED_MOVIES_GRID);
            sortBy = savedInstanceState.getString(SAVED_SORT_PREF);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.sort_menu, menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(LOG_TAG,"Saving instances before destroying the activity");
        if (movieArrayList != null){outState.putParcelableArrayList(SAVED_MOVIES_GRID, movieArrayList);}
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_SORT_PREF, sortBy);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String sortByPopularity = "popularity.desc";
        String sortByRating = "vote_average.desc";
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort_by_popularity) {
            sortBy = sortByPopularity;
            onLoadMoviesEvent();
        }
        else if (id == R.id.action_sort_by_rating) {
            sortBy = sortByRating;
            onLoadMoviesEvent();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        onLoadMoviesEvent();
        movieAdapter = new MovieAdapter(getActivity(), movieArrayList);

        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        gridView.setAdapter(movieAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = movieAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, movie);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
