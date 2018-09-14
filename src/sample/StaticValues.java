package sample;

public enum StaticValues {
    NULL_PLOT ("No plot available, MovieDB dropped the ball."),
    NULL_RESULT_TITLE ("No results found"),
    NULL_RESULT_DESC ("That's right, out of the millions, billions of possible results, you typo'ed so badly that I honestly can't even take a shot."),
    PATH_REMOTE_BASE ("/var/www/html/Video"),
    PATH_REMOTE_DISK ("/mediaone"),
    PATH_REMOTE_MOVIE ("/video/movies"),
    PATH_REMOTE_TV ("/video/tv"),
    PATH_REMOTE_ANIME ("/video/anime"),
    PATH_LOCAL_CONTAINER ("\\Media Manager 4"),
    PATH_LOCAL_DATABASES ("\\Databases"),
    PATH_LOCAL_DISPOSABLE ("\\Disposable"),
    PATH_LOCAL_CATEGORIES ("\\Media Categories"),
    PATH_LOCAL_SERIES ("\\Series"),
    PATH_LOCAL_ARTWORK ("\\Artwork"),
    PATH_LOCAL_CAPTIONS ("\\Captions"),
    PATH_LOCAL_FILES ("\\Files"),
    PATH_LOCAL_SEASON_BW ("\\Season Breakout Written"),
    PATH_LOCAL_SEASON_IW ("\\Season Index Written"),
    PATH_LOCAL_SERIES_IM ("\\Series Index Modified"),
    PATH_LOCAL_SERIES_IW ("\\Series Index Written"),
    DB_NAME_SETTINGS ("\\settings.db"),
    DB_NAME_MEDIA ("\\media.db"),
    DB_TABLE_SETTINGS ("userSettings"),
    DB_TABLE_MOVIE_PREFIX ("movie"),
    DB_TABLE_TV_PREFIX ("television"),
    DB_TABLE_ANIME_PREFIX ("anime"),
    DB_EXISTING_SUFFIX ("_existing"),
    DB_TABLE_NEW_MEDIA ("media_create"),
    DB_SETTING_IP ("ServerIP"),
    DB_SETTING_PORT ("ServerPort"),
    DB_SETTING_USER ("ServerUser"),
    DB_SETTING_PASS ("ServerPass"),
    DB_SETTING_POSTER ("posterRes"),
    DB_SETTING_ROOT_DIR ("rootDir"),
    DB_SETTING_MEDIA_DIR ("prefMedia"),
    DB_SETTING_SUB_DIR ("prefSubs"),
    INDEX_MOVIE ("/movies.html"),
    INDEX_TV ("/tv.html"),
    INDEX_ANIME ("/anime.html"),
    FILE_SUBTITLE ("/subs.vtt"),
    FILE_ARTWORK ("/poster.jpg"),
    FILE_VIDEO ("/mov.mp4"),
    URL_MOVIEDB_ARTWORK ("http://image.tmdb.org/t/p/"),
    KEY_MOVIEDB ("d694ba3fc7a34e0d35fa93d19bcd95f7"),
    KEY_OMDB ("2e9a3c7d");

    private String value;

    StaticValues(String valueString) {
        this.value = valueString;
    }

    public String getValue() {
        return value;
    }
}
