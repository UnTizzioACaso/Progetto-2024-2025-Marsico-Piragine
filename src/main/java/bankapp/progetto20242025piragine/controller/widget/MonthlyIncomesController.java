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

public class MonthlyIncomesController extends WidgetController {

    @FXML
    private VBox monthlyIncomesVBox;

    @Override
    public String getWidgetType(){ return monthlyIncomesVBox.getId();}


    @FXML
    public void showMenu()
    {
        removeWidget();
    }

    @Override
    public void initializer()
    {
        List<Transaction> transactions = TransactionDAO.getCurrentMonthIncome(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
        for(Transaction transaction : transactions)
        {
            Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, transaction);
            monthlyIncomesVBox.getChildren().add(visualTransaction);
        }
    }

}
