package sample;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

public class MediaDescription {

    public static String fetchMediaPlot(String mediaType, String tmdbId) {
        if (parseXmlResults(fetchImdbID(mediaType,tmdbId)) !=null) {
            return parseXmlResults(fetchImdbID(mediaType, tmdbId));
        } else {
            return StaticValues.NULL_PLOT.getValue();
        }
    }

    private static String fetchImdbID(String mediaType, String tmdbId) {
        JsonElement jsonElement = new JsonParser().parse(Search.fetchimdbID(mediaType, tmdbId));
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String imdbId;
        if (!jsonObject.get("imdb_id").isJsonNull()) {
            imdbId = jsonObject.get("imdb_id").getAsString();
        } else {
            imdbId = null;
        } return imdbId;
    }

    private static String parseXmlResults(String imdbId) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(new InputSource(new StringReader(Search.fetchOverview(imdbId))));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            if (xPath.evaluate("/root/@response",document).equals("True")) {
                return xPath.evaluate("/root/movie/@plot", document).replace("&quot;", "\"").replace("N/A", StaticValues.NULL_PLOT.getValue());
            } else {
                return StaticValues.NULL_PLOT.getValue();
            }
        } catch (Exception e) {
            //handled elsewhere.
        }
        return null;
    }



}
