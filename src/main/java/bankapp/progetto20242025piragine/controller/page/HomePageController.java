package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.scene.Node;
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
            FXMLLoader bankAccountLoader = new FXMLLoader (getClass().getResource("/bankapp/progetto20242025piragine/fxml/widget/bankAccount.fxml"));
            Node favouritesCard = favouritesCardLoader.load();
            Node bankAccount = bankAccountLoader.load();
            homePageGridPane.add(favouritesCard, 1, 0);
            GridPane.setMargin(bankAccount, new Insets(0, 10, 0, 0));
            homePageGridPane.add(bankAccount, 0, 0);
        }
        catch (Exception e)
        {

            throw new RuntimeException(e);
        }
    }
}
