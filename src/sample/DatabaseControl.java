package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseControl {

    public DatabaseControl() {
    }

    public DatabaseControl(Controller controller) {
        this.controller = controller;
    }

    private Connection databaseConnect;
    private Controller controller;
    private String DB_PATH = "jdbc:sqlite:C:\\MediaManager\\testing" + StaticValues.PATH_LOCAL_CONTAINER.getValue() + StaticValues.PATH_LOCAL_DATABASES.getValue();

    public void initialDatabaseSync() {
        //check if database exists on media center, if it does download it, else create as new.
    }


    //need to pass database name to this
    public void openConnection(String databaseFilename) {

        try {
            databaseConnect = DriverManager.getConnection(DB_PATH + databaseFilename);
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }
    }

    public void closeConnection() {

        try {
            if (databaseConnect != null) {
                databaseConnect.close();
            }
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }
    }

    public void queueMediaToAdd(String tableName, String title, String pathLocal, String pathRemote, String subtitles, String status, String episodeCount, String animeType, String mediaType) {

        try {
            Statement sqlStatement = databaseConnect.createStatement();
            sqlStatement.execute("CREATE TABLE IF NOT EXISTS " + tableName + " (title TEXT, path_local TEXT, path_remote TEXT, subtitles TEXT, status TEXT, episode_count TEXT, anime_type TEXT, media_type TEXT)");
            sqlStatement.execute("INSERT INTO " + tableName + " (title, path_local, path_remote, subtitles, status) VALUES ('" + title + "', '" + pathLocal + "', '" + pathRemote + "', '" + subtitles + "', '" + status + "', '" + episodeCount + "', '" + animeType + "', '" + mediaType + "')");
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }
    }

    public ResultSet searchExistingMedia(String tableName, String databaseSearchString) {
//needs a lot of work to make it valid for the 19 different fetches we need

        try {
            Statement sqlStatement = databaseConnect.createStatement();
            return sqlStatement.executeQuery("SELECT * FROM " + tableName + " WHERE title LIKE '%" + databaseSearchString + "%'");
        } catch (Exception e) {

        } return null;
    }

    public boolean checkIfTitleOwned(String tableName, String databaseSearchString, String dateString) {
        boolean movieExists = false;

        try {
            Statement sqlStatement = databaseConnect.createStatement();
            ResultSet resultSet = sqlStatement.executeQuery("SELECT * FROM " + tableName + " WHERE title = '" + databaseSearchString + " (" + dateString + ")'");

            if (resultSet.getString("title") != null) {
                movieExists = true;
            }
        } catch (Exception e) {

        } return movieExists;

    }

    public void saveSettings(String tableName, String settingName, String settingValue) {

        try {
            Statement sqlStatement = databaseConnect.createStatement();
            sqlStatement.execute("CREATE TABLE IF NOT EXISTS " + tableName + " (settingName TEXT, settingValue TEXT, CONSTRAINT singular UNIQUE (settingName))");
            sqlStatement.execute("INSERT OR REPLACE INTO " + tableName + " (settingName, settingValue) VALUES ('" + settingName + "', '" + settingValue + "')");
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }
    }

    public String retrieveSettings(String tableName, String settingName) {
        ResultSet settingValue;
        String settingValueFinal = null;

        try {
            Statement sqlStatement = databaseConnect.createStatement();
            settingValue = sqlStatement.executeQuery("SELECT settingValue FROM " + tableName + " WHERE settingName = '" + settingName + "'");
            settingValueFinal = settingValue.getString("SettingValue");

        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }
        return settingValueFinal;
    }

    public ResultSet buildMovieQueueTable(String tableName) {

        try {
            Statement sqlStatement = databaseConnect.createStatement();
            return sqlStatement.executeQuery("SELECT * FROM " + tableName);
        } catch (Exception e) {

        } return null;
    }

}
