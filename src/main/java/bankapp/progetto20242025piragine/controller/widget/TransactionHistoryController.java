package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Transaction;
import bankapp.progetto20242025piragine.db.TransactionDAO;
import bankapp.progetto20242025piragine.util.VisualTransactionCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class TransactionHistoryController extends WidgetController
{
    @FXML
    private GridPane transactionHistoryGridPane;

    @FXML
    private VBox transactionHistoryVBox;

    @Override
    public String getWidgetType(){ return transactionHistoryGridPane.getId();}

    @FXML
    public void search()
    {

    }

    @Override
    public void initializer()
    {
        try
        {
            List<Transaction> transactions = TransactionDAO.getAllTransactionsByAccount(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
            for(Transaction transaction : transactions)
            {
                Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, transaction);
                transactionHistoryVBox.getChildren().add(visualTransaction);
            }
        }
        catch (SQLException e)
        {
            System.err.println("error during getting all transaction " + e.getMessage());
            e.printStackTrace();
        }
    }

}
