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

public class LastFiveExpensesController extends WidgetController
{

    // VBox container that will hold the last five expense transactions
    @FXML
    private VBox lastFiveExpensesVBox;

    // Initializes the widget and loads the last five expenses of the current user
    @Override
    public void initializer()
    {
        List<Transaction> transactions = TransactionDAO.getTransactionsBySender(BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID()));
        for (int i = 0; i < 5; i++)
        {
            if (i >= transactions.size())
            {
                return;
            }
            Node visualTransaction = VisualTransactionCreator.createVisualTransaction(CurrentSession.getRootController(), transactions.get(i));
            lastFiveExpensesVBox.getChildren().add(visualTransaction);
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
