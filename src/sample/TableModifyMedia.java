package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class TableModifyMedia {

    public ObservableList populateModifyMoviesTable(String tableName, String databaseSearchString) {
        ObservableList<ModifyMovies> modifyMovies = FXCollections.observableArrayList();
        DatabaseControl databaseControl = new DatabaseControl();
        databaseControl.openConnection(StaticValues.DB_NAME_MEDIA.getValue());

        ResultSet databaseQueryResults = databaseControl.searchExistingMedia(tableName,databaseSearchString);

        try {
            while (databaseQueryResults.next()) {
                modifyMovies.add(new ModifyMovies(databaseQueryResults.getString("title"),
                        databaseQueryResults.getString("subtitles"),
                        databaseQueryResults.getString("directory")));
            }
        } catch (Exception e) {

        }

        databaseControl.closeConnection();
        return modifyMovies;
    }

    public static class ModifyMovies {
        private SimpleStringProperty title = new SimpleStringProperty("");
        private SimpleStringProperty subs = new SimpleStringProperty("");
        private SimpleStringProperty remotePath = new SimpleStringProperty("");

        public ModifyMovies() {
            this("","","");
        }

        ModifyMovies(String title, String subs, String remotePath) {
            setTitle(title);
            setSubs(subs);
            setRemotePath(remotePath);
        }

        public String getTitle() {
            return title.get();
        }

        private void setTitle(String title) {
            this.title.set(title);
        }

        public String isSubs() {
            return subs.get();
        }

        private void setSubs(String subs) {
            this.subs.set(subs);
        }

        public String getRemotePath() {
            return remotePath.get();
        }

        private void setRemotePath(String remotePath) {
            this.remotePath.set(remotePath);
        }
    }
}
