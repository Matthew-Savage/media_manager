package sample;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Search {

    public static String mediaSearch(String mediaTitle, String mediaType) {
        try {
            return fetchUrl("https://api.themoviedb.org/3/search/" + mediaType + "?include_adult=false&page=1&query=" + mediaTitle.replaceAll("\\s","%20") + "&api_key=" + StaticValues.KEY_MOVIEDB.getValue());
        } catch (Exception e) {
            //handled elsewhere
        } return null;
    }

    public static String fetchimdbID(String mediaType, String tmdbId) {
        try {
            return fetchUrl("https://api.themoviedb.org/3/" + mediaType + "/" + tmdbId + "/external_ids?api_key=" + StaticValues.KEY_MOVIEDB.getValue());
        } catch (Exception e) {
            //handled elsewhere
        } return null;
    }

    public static String fetchOverview(String imdbId) {
        try {
            return fetchUrl("http://www.omdbapi.com/?apikey=" + StaticValues.KEY_OMDB.getValue() + "&plot=short&r=xml&i=" + imdbId);
        } catch (Exception e) {
            //handled elsewhere
        } return null;
    }

    private static String fetchUrl(String address) {
        Response result;
        try {
            Request webAddress = new Request.Builder().url(address).get().build();
            result = new OkHttpClient().newCall(webAddress).execute();
            return result.body().string();
        } catch (Exception e) {
            //not applicable to our use case
        } return null;
    }
}
