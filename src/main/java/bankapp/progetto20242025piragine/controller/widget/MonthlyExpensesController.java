package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Transaction;
import bankapp.progetto20242025piragine.db.TransactionDAO;
import bankapp.progetto20242025piragine.util.VisualTransactionCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
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

    @Override
    public void initializer()
    {
        List<Transaction> transactions = TransactionDAO.getCurrentMonthOutcome(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
        for (Transaction transaction : transactions) {
            Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, transaction);
            monthlyExpensesVBox.getChildren().add(visualTransaction);
        }
    }

}
