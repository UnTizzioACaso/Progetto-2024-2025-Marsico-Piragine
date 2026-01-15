package bankapp.progetto20242025piragine.controller.widget;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MonthlyIncomesController extends WidgetController {

    @FXML
    private VBox monthlyIncomesVBox;

    @Override
    public String getWidgetType(){ return monthlyIncomesVBox.getId();}
}
