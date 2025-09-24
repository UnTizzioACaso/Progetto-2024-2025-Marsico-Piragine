package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.io.IOException;

public class HomePageController extends BranchController
{
    @FXML
    private GridPane homePageGridPane;

    @FXML
    public void initialize() //initializing the home page
    {
        try
        {
            FXMLLoader favouritesCardLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/widget/favouritesCard.fxml"));
            Parent favouritesCard = favouritesCardLoader.load();
            homePageGridPane.add(favouritesCard, 1, 0);
        }
        catch (Exception e)
        {

            throw new RuntimeException(e);
        }
    }
}
