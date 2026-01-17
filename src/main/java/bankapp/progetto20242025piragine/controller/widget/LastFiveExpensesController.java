package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.TransactionController;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Transaction;
import bankapp.progetto20242025piragine.db.TransactionDAO;
import bankapp.progetto20242025piragine.util.VisualTransactionCreator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class LastFiveExpensesController extends WidgetController
{

    // VBox container that will hold the last five expense transactions
    @FXML
    private VBox lastFiveExpensesVBox;

    // Initializes the widget and loads the last five expenses of the current user
    @Override
    public void initializer()
    {
        try
        {
            List<Transaction> transactions = TransactionDAO.getTransactionsBySender(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
            for(int i = 0; i < 5; i++)
            {

                Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, transactions.get(i));
                lastFiveExpensesVBox.getChildren().add(visualTransaction);
            }
        }
        catch (SQLException e)
        {
            System.err.println("error during getting last 5 negative transaction " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void showMenu()
    {
        miniMenuShower(lastFiveExpensesVBox);
    }

    @Override
    public String getWidgetType()
    {
        return lastFiveExpensesVBox.getId();
    }
}
