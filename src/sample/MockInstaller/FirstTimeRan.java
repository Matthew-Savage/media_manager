package sample.MockInstaller;

import sample.Controller;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class FirstTimeRan {

    private Path path = Paths.get(getInitialPath());
    private UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
    private Controller controller;
    private String valueName = "A";

    public FirstTimeRan(Controller controller) {
        this.controller = controller;
    }

    public boolean getMeta() {
        String readValue = null;
        boolean hasRan = false;

        try {
            ByteBuffer buffer = ByteBuffer.allocate(view.size(valueName));
            view.read(valueName, buffer);
            buffer.flip();
            readValue = Charset.defaultCharset().decode(buffer).toString();
        } catch (NoSuchFileException e) {
            //Unavoidable and harmless
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }

        if (readValue == null) {
            hasRan = false;
        } else if (readValue.equals("Ran")){
            hasRan = true;
        }
        return hasRan;
    }

    public void setMeta() {

        try {
            view.write(valueName, Charset.defaultCharset().encode("Ran"));
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }
    }

    private String getInitialPath() {
        String initialPath = null;

        try {
            File whereAt = new File(Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            initialPath = whereAt.toString();
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }
        return initialPath;
    }
}
