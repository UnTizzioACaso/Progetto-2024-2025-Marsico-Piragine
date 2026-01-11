package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.scene.layout.GridPane;

public class AddWidget01Controller extends BranchController
{
    public GridPane homePageGridPane;



    public void disableUsedWidgets(GridPane homePageGridPane)
    {
        this.homePageGridPane = homePageGridPane;
        homePageGridPane.getChildren();

    }

}
