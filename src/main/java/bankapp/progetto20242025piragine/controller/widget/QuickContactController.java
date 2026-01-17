package bankapp.progetto20242025piragine.controller.widget;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class QuickContactController extends WidgetController
{
    @FXML
    private GridPane quickContactGridPane;

    @FXML
    public void showMenu()
    {
        miniMenuShower(quickContactGridPane);
    }

    @Override
    public String getWidgetType(){ return quickContactGridPane.getId();}

}
