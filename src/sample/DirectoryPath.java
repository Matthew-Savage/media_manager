package sample;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class DirectoryPath {

    public String fileChooser(Window stageName, String windowTitle, String fileExtension, String preferredDirectory) {

        File preferredDirectoryToFile = new File(preferredDirectory);
        FileChooser fileChooser = new FileChooser();
        String fileDescription;
        String filePathFinal;

        if (!windowTitle.equals("")) {
            fileChooser.setTitle(windowTitle);
        } else {
            fileChooser.setTitle("Select File");
        }

        if (!fileExtension.equals("")) {
            switch (fileExtension) {
                case "mp4":
                    fileDescription = "Video File";
                    break;
                case "db":
                    fileDescription = "Media Database";
                    break;
                case "srt":
                    fileDescription = "Subtitle File";
                    break;
                default:
                    fileDescription = "File";
                    break;
            }
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(fileDescription, "*." + fileExtension));
        }

        if (!preferredDirectory.equals("")) {
            fileChooser.setInitialDirectory(preferredDirectoryToFile);
        }

        File filePath = fileChooser.showOpenDialog(stageName);

        if (filePath != null) {
            filePathFinal = filePath.toString();
        } else {
            filePathFinal = null;
        } return filePathFinal;
    }

    public String folderChooser(Window stageName, String preferredDirectory) {

        File preferredDirectoryToFile = new File(preferredDirectory);
        DirectoryChooser directoryChooser = new DirectoryChooser();
        String folderPathFinal;

        if (!preferredDirectory.equals("")) {
            directoryChooser.setInitialDirectory(preferredDirectoryToFile);
        }

        File folderPath = directoryChooser.showDialog(stageName);

        if (folderPath != null) {
            folderPathFinal = folderPath.toString();
        } else {
            folderPathFinal = null;
        } return folderPathFinal;
    }
}
