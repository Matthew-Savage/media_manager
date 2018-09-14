package sample;

        import javax.imageio.ImageIO;
        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.net.URL;

public class MediaArtwork {

    private Controller controller;

    public MediaArtwork(Controller controller) {
        this.controller = controller;
    }

    public void saveArtwork(String artworkUrl) {
        String resolution = DynamicValues.getPosterRes();
        String fileExtension = "jpg";
        String artworkLocal = DynamicValues.getRootDir() + StaticValues.PATH_LOCAL_CONTAINER + StaticValues.PATH_LOCAL_DISPOSABLE + StaticValues.PATH_LOCAL_ARTWORK + StaticValues.FILE_ARTWORK;

        try {
            BufferedImage mediaArt = ImageIO.read(new URL(StaticValues.URL_MOVIEDB_ARTWORK + resolution + artworkUrl));
            ImageIO.write(mediaArt, fileExtension, new File(artworkLocal));
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }
    }
}
