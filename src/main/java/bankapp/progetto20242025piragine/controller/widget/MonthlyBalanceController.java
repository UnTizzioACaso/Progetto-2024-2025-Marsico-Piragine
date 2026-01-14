package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class MonthlyBalanceController extends WidgetController {

    @FXML
    private GridPane monthlyBalanceGridPane;


    @Override
    public String getWidgetType()
    {
        return monthlyBalanceGridPane.getId();
    }

}
