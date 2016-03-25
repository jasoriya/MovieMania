package com.shreyans.moviemania.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shreyans on 22-01-2016.
 * Returns the JSON request result.
 */

public class MovieResults implements Parcelable {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Movie> results = new ArrayList<Movie>();
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    /**
     *
     * @return
     *     The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     *
     * @param page
     *     The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     *
     * @return
     *     The results
     */
    public List<Movie> getResults() {
        return results;
    }

    /**
     *
     * @param results
     *     The results
     */
    public void setResults(List<Movie> results) {
        this.results = results;
    }

    /**
     *
     * @return
     *     The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     *
     * @param totalResults
     *     The total_results
     */
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    /**
     *
     * @return
     *     The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     *
     * @param totalPages
     *     The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    private MovieResults(Parcel in) {
        page = in.readInt();
        List<Movie> l = new ArrayList<>();
        in.readList(l, null);
        results = l;
        totalResults = in.readInt();
        totalPages = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(page);
        parcel.writeList(results);
        parcel.writeInt(totalResults);
        parcel.writeInt(totalPages);
    }

    public int describeContents() {return 0;}

    public static final Parcelable.Creator<MovieResults> CREATOR = new Parcelable.Creator<MovieResults>(){
        @Override
        public MovieResults createFromParcel(Parcel parcel) {return new MovieResults(parcel);}

        @Override
        public MovieResults[] newArray(int i) {return new MovieResults[i];}
    };
}

