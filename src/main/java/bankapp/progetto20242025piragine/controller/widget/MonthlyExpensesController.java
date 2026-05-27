package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.dao.TransactionDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.VisualTransactionCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.List;

public class MonthlyExpensesController extends WidgetController {

    @FXML
    private VBox monthlyExpensesVBox;

    @Override
    public String getWidgetType(){ return monthlyExpensesVBox.getId();}

    @FXML
    public void showMenu()
    {
        removeWidget();
    }

    @FXML
    public void initialize()
    {
        List<Transaction> transactions = TransactionDAO.getCurrentMonthOutcome(BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID()));
        for (Transaction transaction : transactions) {
            Node visualTransaction = VisualTransactionCreator.createVisualTransaction(CurrentSession.getRootController(), transaction);
            monthlyExpensesVBox.getChildren().add(visualTransaction);
        }
    }

}
