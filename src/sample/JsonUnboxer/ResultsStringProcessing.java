package sample.JsonUnboxer;

import sample.MediaDescription;
import sample.ParseGenre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ResultsStringProcessing {

    private static List<String> polishedResults;

    public static List<String> getPolishedResults() {
        return polishedResults;
    }

    public static void polishResultStrings(String mediaTitle, String mediaType) {
        JsonToJava jsonToJava = new JsonToJava();
        Scanner scanner = new Scanner(jsonToJava.finalOutput(mediaTitle, mediaType));
        List<String> searchResultsRaw = new ArrayList<>();

        while (scanner.hasNextLine()) {
            searchResultsRaw.add(scanner.nextLine());
        }
        if (searchResultsRaw.size() > 0) {
            polishedResults = convertGenreNumbersToWords(searchResultsRaw);
            polishedResults = convertMediaIDtoDescription(polishedResults,mediaType);
        } else {
            polishedResults = Collections.emptyList();
        }
    }

    private static List<String> convertMediaIDtoDescription(List rawResultsList, String mediaType) {
        int resultAmount = rawResultsList.size() / 5;
        int resultNumber = -3;

        if (resultAmount > 5) {
            resultAmount = 5;
        }

        while (resultAmount > 0) {
            resultNumber = resultNumber + 5;
            resultAmount--;
            rawResultsList.set(resultNumber, MediaDescription.fetchMediaPlot(mediaType,rawResultsList.get(resultNumber).toString()));
        }

        return rawResultsList;
    }

    private static List<String> convertGenreNumbersToWords(List rawResultsList) {
        int resultAmount = rawResultsList.size() / 5;
        int resultNumber = -2;

        if (resultAmount > 5) {
            resultAmount = 5;
        }

        while (resultAmount > 0) {
            resultNumber = resultNumber + 5;
            resultAmount--;
            rawResultsList.set(resultNumber, ParseGenre.genreName(rawResultsList.get(resultNumber).toString()));
        }
        return rawResultsList;
    }
}
