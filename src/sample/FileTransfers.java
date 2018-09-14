package sample;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

import java.io.File;

public class FileTransfers {

    private String serverAddress;
    private String serverPort;
    private String userLogin;
    private String userPassword;
    private String serverConnection;
    private static String testPath = "/";
    private static StandardFileSystemManager fileManager = new StandardFileSystemManager();
    private static Controller controller;

    public FileTransfers() {
    }

    public FileTransfers(Controller controller) {
        this.controller = controller;
    }

    public boolean verifyConnection() {
        boolean connected;

        try {
            fileManager.init();
            FileObject connectionTest = fileManager.resolveFile(serverConnection + testPath,ftpOptions());
            connected = true;
        } catch (Exception e) {
            connected = false;
        } finally {
            fileManager.close();
        } return connected;
    }

    public void initialFileSync() {
        //get most recent files from media center
        //hardcoded paths
    }

    public String nextFreeFolderName(String remoteFilePath, String fileName) {
        int startingFolder = 0;
        String startingFolderString;
        FileObject existingFile;
        try {
            fileManager.init();
            do {
                startingFolder++;
                startingFolderString = String.format("%05d",startingFolder);
                existingFile = fileManager.resolveFile(serverConnection + remoteFilePath + "/" + startingFolderString + fileName, ftpOptions());
            } while (existingFile.exists());
            return startingFolderString;
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        } finally {
            fileManager.close();
        } return "";
    }

    public void uploadMedia(String localFilePath, String remoteFilePath, String mediaType) {
        File folder = new File(localFilePath);
        FileSelector selector = null;

        if (mediaType.equals("movie")) {
            selector = Selectors.SELECT_SELF;
        } else if (mediaType.equals("tv") || mediaType.equals("anime")) {
            selector = Selectors.SELECT_CHILDREN;
        }
        try {
            fileManager.init();
            FileObject localFolder = fileManager.resolveFile(folder.getAbsolutePath());
            FileObject remoteFolder = fileManager.resolveFile(serverConnection + remoteFilePath, ftpOptions());
            remoteFolder.copyFrom(localFolder, selector);
        } catch (FileSystemException e) {
            controller.errorPopup(e.toString());
        } finally {
            fileManager.close();
        }
    }

    public double uploadProgress() {
        double progress = -1;
        return progress;
    }

    private static FileSystemOptions ftpOptions() {
        FileSystemOptions fileSystemOptions = new FileSystemOptions();
        SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(fileSystemOptions,false);
        SftpFileSystemConfigBuilder.getInstance().setTimeout(fileSystemOptions,15000);

        try {
            SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(fileSystemOptions,"no");
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }
        return fileSystemOptions;
    }

    public void initValues() {
        serverAddress = DynamicValues.getServerIP();
        serverPort = DynamicValues.getServerPort();
        userLogin = DynamicValues.getServerUser();
        userPassword = DynamicValues.getServerPass();
        serverConnection = "sftp://" + userLogin + ":" + userPassword + "@" + serverAddress + ":" + serverPort;
    }
}