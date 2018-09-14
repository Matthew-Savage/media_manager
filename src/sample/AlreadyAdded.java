package sample;

import java.util.ArrayList;
import java.util.List;

public class AlreadyAdded {

    private static List<Boolean> movieOwned = new ArrayList<>();

    public static List<Boolean> getMovieOwned() {
        return movieOwned;
    }

    public static void checkIfMovieOwned(String tableName, List polishedResults) {
        getMovieOwned().removeAll(getMovieOwned());
        DatabaseControl databaseControl = new DatabaseControl();
        int resultAmount = polishedResults.size() / 5;
        int resultNumber = -4;

        if (resultAmount > 5) {
            resultAmount = 5;
        }

        databaseControl.openConnection(StaticValues.DB_NAME_MEDIA.getValue());
        while (resultAmount > 0) {
            resultNumber = resultNumber + 5;
            resultAmount--;
            movieOwned.add(databaseControl.checkIfTitleOwned(tableName, polishedResults.get(resultNumber).toString(), polishedResults.get(resultNumber + 4).toString()));
        }
        databaseControl.closeConnection();
    }
}