package bankapp.progetto20242025piragine.util;

import javafx.scene.Scene;

public class ThemeManager {

    private static boolean darkMode = false; // stato globale del tema

    /**
     * Restituisce true se il tema corrente è scuro
     */
    public static boolean isDarkMode() {
        return darkMode;
    }

    /**
     * Applica il tema a una scena
     */
    public static void applyTheme(Scene scene) {
        if (scene == null) return;

        scene.getStylesheets().clear();

        String cssPath = darkMode
                ? "/bankapp/progetto20242025piragine/css/dark.css"
                : "/bankapp/progetto20242025piragine/css/light.css";

        scene.getStylesheets().add(ThemeManager.class.getResource(cssPath).toExternalForm());
    }

    /**
     * Imposta il tema chiaro/scuro e applica alla scena
     */
    public static void setDarkMode(Scene scene, boolean dark) {
        darkMode = dark;
        applyTheme(scene);

    }

    /**
     * Inverte il tema corrente e applica alla scena
     */
    public static void toggleTheme(Scene scene) {
        darkMode = !darkMode;
        applyTheme(scene);
    }
}
