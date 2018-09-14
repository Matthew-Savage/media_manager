package sample.JsonUnboxer;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ResultsBodyDeserialize implements JsonDeserializer<ResultsBodyDeserialize.ResultsBody> {

    @Override
    public ResultsBody deserialize(JsonElement json, Type typeOFT, JsonDeserializationContext context) {
        JsonObject jsonObject = json.getAsJsonObject();

        ResultsBody resultsBody = new ResultsBody();

        resultsBody.setPosterPath(jsonObject.get("poster_path").toString().replace("\\","").replace("\"",""));
        resultsBody.setId(jsonObject.get("id").toString());
        resultsBody.setGenreIds(jsonObject.get("genre_ids").toString().replace("[","").replace("]",""));

        if (jsonObject.has("title")) {
            resultsBody.setTitle(jsonObject.get("title").getAsString());
        } else {
            resultsBody.setTitle(jsonObject.get("name").getAsString());
        }

        if (jsonObject.has("release_date")) {
                try {
                    resultsBody.setRelease_date(jsonObject.get("release_date").toString().replace("\"", "").substring(0, 4));
                } catch (StringIndexOutOfBoundsException e) {
                    resultsBody.setRelease_date("N/A");
                }
        } else {
            resultsBody.setRelease_date("");
        } return resultsBody;
    }

    public class ResultsBody {
        private String id;
        private String title;
        private String posterPath;
        private String genreIds;
        private String release_date;

        private void setTitle(String title) {
            this.title = title;
        }

        private void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        private void setGenreIds(String genreIds) {
            this.genreIds = genreIds;
        }

        private void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        private void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return String.format("%s\n%s\n%s\n%s\n%s", title, id, genreIds, posterPath, release_date);
        }
    }
}