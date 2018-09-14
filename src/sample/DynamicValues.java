package sample;

public class DynamicValues {

    private static String serverIP;
    private static String serverPort;
    private static String serverUser;
    private static String serverPass;
    private static String posterRes;
    private static String rootDir;
    private static String mediaDir;
    private static String subDir;

    public static String getServerIP() {
        return serverIP;
    }

    public static String getServerPort() {
        return serverPort;
    }

    public static String getServerUser() {
        return serverUser;
    }

    public static String getServerPass() {
        return serverPass;
    }

    public static String getPosterRes() {
        return posterRes;
    }

    public static String getRootDir() {
        return rootDir;
    }

    public static String getMediaDir() {
        return mediaDir;
    }

    public static String getSubDir() {
        return subDir;
    }

    public static void fetchValues() {
        DatabaseControl databaseControl = new DatabaseControl();

        databaseControl.openConnection(StaticValues.DB_NAME_SETTINGS.getValue());
        serverIP = databaseControl.retrieveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_IP.getValue());
        serverPort = databaseControl.retrieveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_PORT.getValue());
        serverUser = databaseControl.retrieveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_USER.getValue());
        serverPass = databaseControl.retrieveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_PASS.getValue());
        posterRes = databaseControl.retrieveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_POSTER.getValue());
        rootDir = databaseControl.retrieveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_ROOT_DIR.getValue());
        mediaDir = databaseControl.retrieveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_MEDIA_DIR.getValue());
        subDir = databaseControl.retrieveSettings(StaticValues.DB_TABLE_SETTINGS.getValue(), StaticValues.DB_SETTING_SUB_DIR.getValue());
        databaseControl.closeConnection();
    }
}
