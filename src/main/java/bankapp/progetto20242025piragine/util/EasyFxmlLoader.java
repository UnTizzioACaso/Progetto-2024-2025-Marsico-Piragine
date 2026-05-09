package bankapp.progetto20242025piragine.util;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.util.Pair;

public class EasyFxmlLoader {



    public static Pair<BranchController, Node> loader(String fxmlPath) {
        Pair<BranchController, Node> p = new Pair<>(null, null);
        FXMLLoader loader = new FXMLLoader(EasyFxmlLoader.class.getResource(fxmlPath));
        try {
            Node node = loader.load();
            BranchController controller = loader.getController();
            p = new Pair<>(controller, node);
        } catch (Exception e) {
            System.err.println("error loading" + fxmlPath + " object: " + e.getMessage());
            e.printStackTrace();
        }
        return p;
    }

}
