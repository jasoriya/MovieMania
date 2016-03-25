package com.shreyans.moviemania.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Shreyans on 25-03-2016.
 * MovieContract for storing favorite movies in database.
 */
public final class MovieContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public MovieContract(){}

    public static final String LOG_TAG = MovieContract.class.getSimpleName();
    public static final String CONTENT_AUTHORITY =  "com.shreyans.moviemania.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /* Inner class that defines the table contents */
    public static final class MovieEntry implements BaseColumns {

        //Table Name
        public static final String TABLE_NAME = "movie";
        //Columns
        public static final String _ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_BACKDROP = "backdrop";
        public static final String COLUMN_POSTER = "poster";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_AVG_VOTE = "avg_vote";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_OVERVIEW = "plot_summary";
        public static final String COLUMN_POPULARITY = "popularity";
    }
    //Comment
}
