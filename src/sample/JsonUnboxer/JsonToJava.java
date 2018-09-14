package sample.JsonUnboxer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sample.Search;

public class JsonToJava {

    public String finalOutput(String mediaTitle, String mediaType) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ResultsHeaderDeserialize.ResultsHeader.class, new ResultsHeaderDeserialize());
        gsonBuilder.registerTypeAdapter(ResultsBodyDeserialize.ResultsBody.class, new ResultsBodyDeserialize());
        Gson gson = gsonBuilder.create();

        ResultsHeaderDeserialize.ResultsHeader searchResults = gson.fromJson(Search.mediaSearch(mediaTitle,mediaType), ResultsHeaderDeserialize.ResultsHeader.class);
        return searchResults.toString();
    }
}
