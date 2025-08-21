/*Create the data to be loaded and extrated*/

package com.example.flixster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    Double voteAverage;
    public Movie() {}

    public Movie(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("title");
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        voteAverage = jsonObject.getDouble("vote_average");
        overview = jsonObject.getString("overview");
    }
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        int len = movieJsonArray.length();
        for (int i = 0; i < len; i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);  // TODO: video (ep 1) at 20:37, replace hard code
    }
    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w780/%s", backdropPath);
    }
    public String getTitle() {
        return title;
    }
    public String getOverview() {
        return overview;
    }
    public Double getVoteAverage() {
        return voteAverage;
    }
}
