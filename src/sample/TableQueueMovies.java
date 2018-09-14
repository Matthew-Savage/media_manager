package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class TableQueueMovies {

    public ObservableList newMovieQueueTable(String tableName) {
        ObservableList<QueueMovieFields> queueMovie = FXCollections.observableArrayList();
        DatabaseControl databaseControl = new DatabaseControl(null);
        databaseControl.openConnection(StaticValues.DB_NAME_MEDIA.getValue());

        ResultSet queuedMovieList = databaseControl.buildMovieQueueTable(tableName);

        try {
            while (queuedMovieList.next()) {
                queueMovie.add(new QueueMovieFields(queuedMovieList.getString("title"),
                        queuedMovieList.getString("path_local"),
                        queuedMovieList.getString("path_remote"),
                        queuedMovieList.getString("subtitles"),
                        queuedMovieList.getString("status")));
                System.out.println(queuedMovieList.getString("status"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        databaseControl.closeConnection();
        return queueMovie;
    }

    public static class QueueMovieFields {
        //variables are final in other project
        private SimpleStringProperty title = new SimpleStringProperty("");
        private SimpleStringProperty subs = new SimpleStringProperty("");
        private SimpleStringProperty localPath = new SimpleStringProperty("");
        private SimpleStringProperty remotePath = new SimpleStringProperty("");
        private SimpleStringProperty status = new SimpleStringProperty("");

        public QueueMovieFields() {
            this("","","","","");
        }

        QueueMovieFields(String title, String localPath, String remotePath, String subs, String status) {
            setTitle(title);
            setLocalPath(localPath);
            setRemotePath(remotePath);
            setSubs(subs);
            setStatus(status);
        }

        private void setTitle(String title) {
            this.title.set(title);
        }

        private void setLocalPath(String localPath) {
            this.localPath.set(localPath);
        }

        private void setRemotePath(String remotePath) {
            this.remotePath.set(remotePath);
        }

        private void setSubs(String subs) {
            this.subs.set(subs);
        }

        private void setStatus(String status) {
            this.status.set(status);
        }

        public String getTitle() {
            return title.get();
        }

        public String getSubs() {
            return subs.get();
        }

        public String getLocalPath() {
            return localPath.get();
        }

        public String getRemotePath() {
            return remotePath.get();
        }

        public String getStatus() {
            return status.get();
        }
    }
}