package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.JsonUnboxer.ResultsStringProcessing;
import sample.MockInstaller.FirstTimeRan;

import java.util.StringJoiner;

public class Controller {

    private FirstTimeRan firstTimeRan = new FirstTimeRan(this);
    private FileTransfers fileTransfers = new FileTransfers(this);
    private DirectoryPath directoryPath = new DirectoryPath();
    private DatabaseControl databaseControl = new DatabaseControl(this);
    private double xAxis;
    private double yAxis;
    private String localPath;
    private String remotePath;
    private String subtitles = "No";
    private String subtitlePath;
    private String statusValue = "Pending";

    @FXML
    private Pane draggablePane;

    @FXML
    private Pane settingsPane;

    @FXML
    private Pane animeOptions;

    @FXML
    private Pane epCount;

    @FXML
    private Pane ovaCount;

    @FXML
    private Pane errorPane;

    @FXML
    private Pane searchingPane;

    @FXML
    private Pane greetingPane;

    @FXML
    private Pane helpPane;

    @FXML
    private Pane createPane;

    @FXML
    private Pane modifyPane;

    @FXML
    private Pane movieExistsPane;

    @FXML
    private TextField titleText;

    @FXML
    private TextField dateText;

    @FXML
    private TextField searchText;

    @FXML
    private TextField serverAddress;

    @FXML
    private TextField serverPort;

    @FXML
    private TextField serverUser;

    @FXML
    private PasswordField serverPass;

    @FXML
    private TextField rootDirectory;

    @FXML
    private TextField preferredMediaDirectory;

    @FXML
    private TextField preferredSubtitleDirectory;

    @FXML
    private TextArea descText;

    @FXML
    private TextArea errorPaneText;

    @FXML
    private CheckBox helpToggle;

    @FXML
    private RadioButton mediaTypeMovie;

    @FXML
    private RadioButton mediaTypeTelevision;

    @FXML
    private RadioButton mediaTypeAnime;

    @FXML
    private RadioButton taskMediaCreate;

    @FXML
    private RadioButton taskMediaModify;

    @FXML
    private RadioButton w154;

    @FXML
    private RadioButton w185;

    @FXML
    private RadioButton w342;

    @FXML
    private RadioButton w500;

    @FXML
    private RadioButton resultsButtonOne;

    @FXML
    private RadioButton resultsButtonTwo;

    @FXML
    private RadioButton resultsButtonThree;

    @FXML
    private RadioButton resultsButtonFour;

    @FXML
    private RadioButton resultsButtonFive;

    @FXML
    private Button appClose;

    @FXML
    private Button appMinimize;

    @FXML
    private Button sourceSet;

    @FXML
    private Button subtitlesSet;

    @FXML
    private Button settingsButton;

    @FXML
    private Button applyButton;

    @FXML
    private Button errorPaneButton;

    @FXML
    private Button start;

    @FXML
    private TableView mediaQueue;

    @FXML
    private TableColumn mediaQueueTitle;

    @FXML
    private TableColumn mediaQueueSubs;

    @FXML
    private TableColumn mediaQueuePathL;

    @FXML
    private TableColumn mediaQueuePathR;

    @FXML
    private TableColumn mediaQueueStatus;

    @FXML
    private TableView moviesModify;

    @FXML
    private TableColumn moviesModifyTitle;

    @FXML
    private TableColumn moviesModifyRemotePath;

    @FXML
    private TableColumn moviesModifySubs;

    @FXML
    private TableView televisionAnimeModify;

    @FXML
    private TableColumn televisionAnimeModifyTitle;

    @FXML
    private TableColumn televisionAnimeModifyRemotePath;

    @FXML
    private TableColumn televisionAnimeSubs;

    @FXML
    private TableColumn televisionAnimeModifyEpNumber;

    ////        titleText.textProperty().bind(searchText.textProperty());
//        // lol bind experiment, cool

    public void initialize() {
        searchText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (taskMediaModify.isSelected()) {
                    tableMediaModify();
                }
            }
        });

        DynamicValues.fetchValues();
        serverAddress.setText(DynamicValues.getServerIP());
        serverPort.setText(DynamicValues.getServerPort());
        serverUser.setText(DynamicValues.getServerUser());
        serverPass.setText(DynamicValues.getServerPass());
        switch (DynamicValues.getPosterRes()) {
            case "w154":
                w154.setSelected(true);
                break;
            case "w185":
                w185.setSelected(true);
                break;
            case "w342":
                w342.setSelected(true);
                break;
            case "w500":
                w500.setSelected(true);
                break;
        }
        rootDirectory.setText(DynamicValues.getRootDir());
        preferredMediaDirectory.setText(DynamicValues.getMediaDir());
        preferredSubtitleDirectory.setText(DynamicValues.getSubDir());
        fileTransfers.initValues();
//
//        if (!firstTimeRan.getMeta()){
//            getInitialSettings();
//        } else {
//            DynamicValues.fetchValues();
//            serverAddress.setText(DynamicValues.getServerIP());
//            serverPort.setText(DynamicValues.getServerPort());
//            serverUser.setText(DynamicValues.getServerUser());
//            serverPass.setText(DynamicValues.getServerPass());
//
//            switch (DynamicValues.getPosterRes()) {
//                case "w154": w154.setSelected(true);
//                    break;
//                case "w185": w185.setSelected(true);
//                    break;
//                case "w342": w342.setSelected(true);
//                    break;
//                case "w500": w500.setSelected(true);
//                    break;
//            }
//
//            rootDirectory.setText(DynamicValues.getRootDir());
//            preferredMediaDirectory.setText(DynamicValues.getMediaDir());
//            preferredSubtitleDirectory.setText(DynamicValues.getSubDir());
//        }
    }


    //begin custom stage controls
    public void initCoords(MouseEvent event) {
        Stage stage = (Stage) draggablePane.getScene().getWindow();
        xAxis = stage.getX() - event.getScreenX();
        yAxis = stage.getY() - event.getScreenY();
    }

    public void changeCoords(MouseEvent event) {
        Stage stage = (Stage) draggablePane.getScene().getWindow();
        stage.setX(event.getScreenX() + xAxis);
        stage.setY(event.getScreenY() + yAxis);
    }

    public void appClose() {
        Stage stage = (Stage) appClose.getScene().getWindow();
        stage.close();
    }

    public void appMinimize() {
        Stage stage = (Stage) appMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void settingsButton() {
        settingsPane.setVisible(true);
    }
    //end custom stage controls

    public void extraControls() {
        //dictates whether series/anime specific options and settings are displayed
        if (taskMediaModify.isSelected()) {
            tableMediaModify();
        }

        if (mediaTypeMovie.isSelected()) {
            animeOptions.setVisible(false);
            epCount.setVisible(false);
            ovaCount.setVisible(false);
        }
        if (mediaTypeTelevision.isSelected()) {
            animeOptions.setVisible(false);
            ovaCount.setVisible(false);
            epCount.setVisible(true);
        }
        if (mediaTypeAnime.isSelected()) {
            ovaCount.setVisible(false);
            animeOptions.setVisible(true);
            epCount.setVisible(true);
        }
    }

    public void animeMovieSel() {
        epCount.setVisible(false);
        ovaCount.setVisible(false);
    }

    public void animeOvaSel() {
        epCount.setVisible(false);
        ovaCount.setVisible(true);
    }

    public void animeSeriesSel() {
        ovaCount.setVisible(false);
        epCount.setVisible(true);
    }

    public void searchBegin() {
        if (taskMediaCreate.isSelected()) {
            newMediaSearch();
        } else if (taskMediaModify.isSelected()) {
            existingMediaSearch();
        }
    }

    private void existingMediaSearch() {
        tableMediaModify();
    }

    private void newMediaSearch() {
        searchingPane.setVisible(true);

        Runnable runSearch = () -> {
            ResultsStringProcessing.polishResultStrings(searchText.getText(), searchType());
            AlreadyAdded.checkIfMovieOwned(StaticValues.DB_TABLE_MOVIE_PREFIX.getValue() + StaticValues.DB_EXISTING_SUFFIX.getValue(), ResultsStringProcessing.getPolishedResults());
            searchComplete();
        };
        new Thread(runSearch).start();
    }

    private void searchComplete() {

        resultsButtonOne.setSelected(true);
        resultsButtonOnePressed();

        if (ResultsStringProcessing.getPolishedResults().size() >= 22) {
            resultsButtonTwo.setVisible(true);
            resultsButtonThree.setVisible(true);
            resultsButtonFour.setVisible(true);
            resultsButtonFive.setVisible(true);
        } else if (ResultsStringProcessing.getPolishedResults().size() >= 17 && ResultsStringProcessing.getPolishedResults().size() < 22) {
            resultsButtonTwo.setVisible(true);
            resultsButtonThree.setVisible(true);
            resultsButtonFour.setVisible(true);
            resultsButtonFive.setVisible(false);
        } else if (ResultsStringProcessing.getPolishedResults().size() >= 12 && ResultsStringProcessing.getPolishedResults().size() < 17) {
            resultsButtonTwo.setVisible(true);
            resultsButtonThree.setVisible(true);
            resultsButtonFour.setVisible(false);
            resultsButtonFive.setVisible(false);
        } else if (ResultsStringProcessing.getPolishedResults().size() >= 7 && ResultsStringProcessing.getPolishedResults().size() < 12) {
            resultsButtonTwo.setVisible(true);
            resultsButtonThree.setVisible(false);
            resultsButtonFour.setVisible(false);
            resultsButtonFive.setVisible(false);
        } else {
            resultsButtonTwo.setVisible(false);
            resultsButtonThree.setVisible(false);
            resultsButtonFour.setVisible(false);
            resultsButtonFive.setVisible(false);
        }
        searchingPane.setVisible(false);
    }

    private String searchType() {
        String typeOfSearch;

        if (mediaTypeMovie.isSelected()) {
            typeOfSearch = "movie";
        } else {
            typeOfSearch = "tv";
        }
        return typeOfSearch;
    }

    private String mediaType() {
        String mediaTypeSelected = "";

        if (mediaTypeMovie.isSelected()) {
            mediaTypeSelected = "movie";
        } else if (mediaTypeTelevision.isSelected()) {
            mediaTypeSelected = "television";
        } else if (mediaTypeAnime.isSelected()) {
            mediaTypeSelected = "anime";
        }
        return mediaTypeSelected;
    }

    private String taskType() {
        String typeOfTask = "";

        if (taskMediaCreate.isSelected()) {
            typeOfTask = "create";
        } else if (taskMediaModify.isSelected()) {
            typeOfTask = "existing";
        }
        return typeOfTask;
    }

    public void resultsButtonOnePressed() {

        if (ResultsStringProcessing.getPolishedResults().size() > 0) {
            titleText.setText(ResultsStringProcessing.getPolishedResults().get(1));
            descText.setText(ResultsStringProcessing.getPolishedResults().get(2));
            dateText.setText(ResultsStringProcessing.getPolishedResults().get(5));
            movieExistsPane.setVisible(AlreadyAdded.getMovieOwned().get(0));
        } else {
            titleText.setText(StaticValues.NULL_RESULT_TITLE.getValue());
            descText.setText(StaticValues.NULL_RESULT_DESC.getValue());
        }
    }

    public void resultsButtonTwoPressed() {
        titleText.setText(ResultsStringProcessing.getPolishedResults().get(6));
        descText.setText(ResultsStringProcessing.getPolishedResults().get(7));
        dateText.setText(ResultsStringProcessing.getPolishedResults().get(10));
        movieExistsPane.setVisible(AlreadyAdded.getMovieOwned().get(1));
    }

    public void resultsButtonThreePressed() {
        titleText.setText(ResultsStringProcessing.getPolishedResults().get(11));
        descText.setText(ResultsStringProcessing.getPolishedResults().get(12));
        dateText.setText(ResultsStringProcessing.getPolishedResults().get(15));
        movieExistsPane.setVisible(AlreadyAdded.getMovieOwned().get(2));
    }

    public void resultsButtonFourPressed() {
        titleText.setText(ResultsStringProcessing.getPolishedResults().get(16));
        descText.setText(ResultsStringProcessing.getPolishedResults().get(17));
        dateText.setText(ResultsStringProcessing.getPolishedResults().get(20));
        movieExistsPane.setVisible(AlreadyAdded.getMovieOwned().get(3));
    }

    public void resultsButtonFivePressed() {
        titleText.setText(ResultsStringProcessing.getPolishedResults().get(21));
        descText.setText(ResultsStringProcessing.getPolishedResults().get(22));
        dateText.setText(ResultsStringProcessing.getPolishedResults().get(25));
        movieExistsPane.setVisible(AlreadyAdded.getMovieOwned().get(4));
    }

//        System.out.println(DynamicValues.getServerIP());


//        DirectoryPath directoryPath = new DirectoryPath(this);

//        String folderPath = directoryPath.folderChooser(searchText.getScene().getWindow());

//        String folderPath = directoryPath.fileChooser(searchText.getScene().getWindow(),null, "srt",null);

//        System.out.println(folderPath);

//        FirstTimeRan ran = new FirstTimeRan();
//        ran.getMeta();

//        IndexFile indexFile = new IndexFile(this);
//
//        indexFile.createFiles(24);


    public void applyMediaModification() {
        int position = moviesModify.getSelectionModel().getFocusedIndex();

        ObservableList<TableModifyMedia.ModifyMovies> list = moviesModify.getSelectionModel().getSelectedItems();


        for (TableModifyMedia.ModifyMovies lists : list) {
            System.out.println(lists.getTitle() + " - " + lists.isSubs() + " - " + lists.getRemotePath());
        }

        System.out.println(position);
    }


    public void errorPopup(String error) {
        errorPane.setVisible(true);
        errorPaneText.setText(error);
    }

    public void errorPopupClose() {
        errorPane.setVisible(false);
    }

    public void settingsSetDirectory(ActionEvent event) {
        Button button = (Button) event.getSource();

        switch (button.getId()) {
            case "dirButton0":
                rootDirectory.setText(directoryPath.folderChooser(button.getScene().getWindow(), ""));
                break;
            case "dirButton1":
                preferredMediaDirectory.setText(directoryPath.folderChooser(button.getScene().getWindow(), ""));
                break;
            case "dirButton2":
                preferredSubtitleDirectory.setText(directoryPath.folderChooser(button.getScene().getWindow(), ""));
                break;
        }
    }

    public void add() {
        databaseControl.openConnection(StaticValues.DB_NAME_MEDIA.getValue());
        databaseControl.queueMediaToAdd(mediaType() + "_" + taskType(), titleText.getText() + " " + dateText.getText(), localPath, fileTransfers.nextFreeFolderName(StaticValues.PATH_REMOTE_BASE.getValue() + StaticValues.PATH_REMOTE_DISK.getValue() + StaticValues.PATH_REMOTE_MOVIE.getValue(), StaticValues.FILE_VIDEO.getValue()), subtitles, statusValue, "", "", mediaType());
        tableMediaNew();
        //last step of add needs to be resetting values back to default, clearing shit out, ect
        //needs to run on its own thread
    }

    public void getInitialSettings() {
        settingsPane.setVisible(true);
        greetingPane.setVisible(true);
        helpPane.setVisible(true);
        helpToggle.setDisable(true);
    }

    public void helpToggle() {

        if (helpToggle.isSelected()) {
            helpPane.setVisible(true);
        } else {
            helpPane.setVisible(false);
        }
    }

    public void applyButton() {
        String posterResolution = "null";

        if (!firstTimeRan.getMeta()) {
            firstTimeRan.setMeta();
        }

        if (w154.isSelected()) {
            posterResolution = "w154";
        } else if (w185.isSelected()) {
            posterResolution = "w185";
        } else if (w342.isSelected()) {
            posterResolution = "w342";
        } else if (w500.isSelected()) {
            posterResolution = "w500";
        }

        databaseControl.openConnection(StaticValues.DB_NAME_SETTINGS.getValue());
        databaseControl.saveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_IP.getValue(), serverAddress.getText());
        databaseControl.saveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_PORT.getValue(), serverPort.getText());
        databaseControl.saveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_USER.getValue(), serverUser.getText());
        databaseControl.saveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_PASS.getValue(), serverPass.getText());
        databaseControl.saveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_POSTER.getValue(), posterResolution);
        databaseControl.saveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_ROOT_DIR.getValue(), rootDirectory.getText());
        databaseControl.saveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_MEDIA_DIR.getValue(), preferredMediaDirectory.getText());
        databaseControl.saveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_SUB_DIR.getValue(), preferredSubtitleDirectory.getText());


        databaseControl.closeConnection();
        DynamicValues.fetchValues();
        fileTransfers.initValues();

        if (fileTransfers.verifyConnection()) {
            errorPopup("we did it boys");

            settingsPane.setVisible(false);
//            popup saying changes saved
        } else {
            errorPopup("FTP Connection invalid fool");
//         is exception throw necessary afterall?
//            throw new Exception();
        }
    }

    public void taskMediaModify() {
        searchText.clear();
        createPane.setVisible(false);
        modifyPane.setVisible(true);
        tableMediaModify();
    }

    public void taskMediaCreate() {
        createPane.setVisible(true);
        modifyPane.setVisible(false);
    }

    public void tableMediaNew() {

        TableQueueMovies queueMovies = new TableQueueMovies();
        StringJoiner stringJoiner = new StringJoiner("_");
        stringJoiner.add(mediaType()).add("create");
        ObservableList<TableQueueMovies.QueueMovieFields> populateTable = queueMovies.newMovieQueueTable(stringJoiner.toString());

        mediaQueueTitle.setCellValueFactory(new PropertyValueFactory<TableQueueMovies.QueueMovieFields, String>("title"));
        mediaQueuePathL.setCellValueFactory(new PropertyValueFactory<TableQueueMovies.QueueMovieFields, String>("localPath"));
        mediaQueuePathR.setCellValueFactory(new PropertyValueFactory<TableQueueMovies.QueueMovieFields, String>("remotePath"));
        mediaQueueSubs.setCellValueFactory(new PropertyValueFactory<TableQueueMovies.QueueMovieFields, String>("subs"));
        mediaQueueStatus.setCellValueFactory(new PropertyValueFactory<TableQueueMovies.QueueMovieFields, String>("status"));

        mediaQueue.setItems(populateTable);

    }

    private void tableMediaModify() {
        TableModifyMedia modifyMovies = new TableModifyMedia();
        StringJoiner stringJoiner = new StringJoiner("_");
        stringJoiner.add(mediaType()).add("existing");
        ObservableList<TableModifyMedia.ModifyMovies> populateTable = modifyMovies.populateModifyMoviesTable(stringJoiner.toString(), searchText.getText());

        moviesModifyTitle.setCellValueFactory(new PropertyValueFactory<TableModifyMedia.ModifyMovies, String>("title"));
        moviesModifyRemotePath.setCellValueFactory(new PropertyValueFactory<TableModifyMedia.ModifyMovies, String>("remotePath"));
        moviesModifySubs.setCellValueFactory(new PropertyValueFactory<TableModifyMedia.ModifyMovies, Boolean>("subs"));

        moviesModify.setItems(populateTable);
    }

    @FXML
    private void setSource() {
        localPath = directoryPath.fileChooser(sourceSet.getScene().getWindow(),"Select Media File","mp4",DynamicValues.getMediaDir());

    }

    @FXML
    private void setSubtitles() {
        //this will equal a value as well, or i guess maybe not actually, we'll see
        subtitlePath = directoryPath.fileChooser(subtitlesSet.getScene().getWindow(),"Select Subtitle File","srt",DynamicValues.getSubDir());
        subtitles = "Yes";
    }

    public void loltest() {
        String loltestpath;
        loltestpath = directoryPath.folderChooser(sourceSet.getScene().getWindow(), "" );

//        fileTransfers.loltest(loltestpath,"/var/www/html/Video/mediatwo/video/tv/testdir");
    }

    public void startProcessingQueue() {

    }
}
