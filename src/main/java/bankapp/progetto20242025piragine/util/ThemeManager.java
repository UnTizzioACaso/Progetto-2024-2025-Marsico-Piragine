package bankapp.progetto20242025piragine.util;

import javafx.scene.Scene;

public class ThemeManager {

    public static void applyTheme(Scene scene, String themeName)
    {
        if (scene == null) return;
        String cssPath;
        scene.getStylesheets().clear();
        if (themeName.equals("dark")) { cssPath = "/bankapp/progetto20242025piragine/css/dark.css";}
        else if (themeName.equals("light")) { cssPath = "/bankapp/progetto20242025piragine/css/light.css";}
        else {return;}

        scene.getStylesheets().add(ThemeManager.class.getResource(cssPath).toExternalForm());
    }

}
