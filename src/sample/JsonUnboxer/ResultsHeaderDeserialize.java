package sample.JsonUnboxer;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ResultsHeaderDeserialize implements JsonDeserializer<ResultsHeaderDeserialize.ResultsHeader> {

    @Override
    public ResultsHeader deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject jsonObject = json.getAsJsonObject();
        ResultsHeader resultsHeader = new ResultsHeader();

        ResultsBodyDeserialize.ResultsBody[] results = context.deserialize(jsonObject.get("results"), ResultsBodyDeserialize.ResultsBody[].class);

        resultsHeader.setResults(results);
        return resultsHeader;
    }

    public class ResultsHeader {

        private ResultsBodyDeserialize.ResultsBody[] results;

        private void setResults(ResultsBodyDeserialize.ResultsBody[] results) {
            this.results = results;
        }

        @Override
        public String toString() {
            StringBuilder formatted = new StringBuilder();
            for (ResultsBodyDeserialize.ResultsBody result : results) {
                formatted.append("\n").append(result);
            }return formatted.toString();
        }
    }
}
