package sample.MockInstaller;

import sample.Controller;
import sample.FileTransfers;
import sample.StaticValues;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Unboxer {

    private FileTransfers fileTransfers = new FileTransfers();
    private String baseDirectory;
    private String baseContainer = StaticValues.PATH_LOCAL_CONTAINER.getValue();
    private String databases = StaticValues.PATH_LOCAL_DATABASES.getValue();
    private String disposable = StaticValues.PATH_LOCAL_DISPOSABLE.getValue();
    private String mediaCategories = StaticValues.PATH_LOCAL_CATEGORIES.getValue();
    private String series = StaticValues.PATH_LOCAL_SERIES.getValue();
    private String disposableArtwork = disposable + StaticValues.PATH_LOCAL_ARTWORK.getValue();
    private String disposableCaptions = disposable + StaticValues.PATH_LOCAL_CAPTIONS.getValue();
    private String disposableFiles = disposable + StaticValues.PATH_LOCAL_FILES.getValue();
    private String disposableFilesSeasonBreakoutWritten = disposableFiles + StaticValues.PATH_LOCAL_SEASON_BW.getValue();
    private String disposableFilesSeasonIndexWritten = disposableFiles + StaticValues.PATH_LOCAL_SEASON_IW.getValue();
    private String disposableFilesSeriesIndexModified = disposableFiles + StaticValues.PATH_LOCAL_SERIES_IM.getValue();
    private String disposableFilesSeriesIndexWritten = disposableFiles + StaticValues.PATH_LOCAL_SERIES_IW.getValue();
    private Controller controller;

    public Unboxer(Controller controller) {
        this.controller = controller;
    }

    public void unboxingProcess(String rootDirectory) {
        baseDirectory = rootDirectory;

        createDirs(databases);
        createDirs(mediaCategories);
        createDirs(series);
        createDirs(disposableArtwork);
        createDirs(disposableCaptions);
        createDirs(disposableFilesSeasonBreakoutWritten);
        createDirs(disposableFilesSeasonIndexWritten);
        createDirs(disposableFilesSeriesIndexModified);
        createDirs(disposableFilesSeriesIndexWritten);

//        if (fileTransfers.nextFreeFolderName("who/knows","whoknows.html")) {
//            retrieveFiles();
//        } else {
//            createFiles();
//        }

    }

    private void createDirs(String fullPath) {
        try {
            Files.createDirectories(Paths.get(baseDirectory + baseContainer + fullPath));
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }
    }

    private void retrieveFiles() {

    }

    private void createFiles() {

    }

    private void verifyUnboxing() {

    }
}
