package bankapp.progetto20242025piragine.controller.widget;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class TransactionHistoryController extends WidgetController
{
    @FXML
    private GridPane transactionHistoryGridPane;

    @Override
    public String getWidgetType(){ return transactionHistoryGridPane.getId();}

    @FXML
    public void search()
    {

    }
}
