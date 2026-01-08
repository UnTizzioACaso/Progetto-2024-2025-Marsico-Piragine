package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.TransactionController;
import bankapp.progetto20242025piragine.db.Transaction;
import bankapp.progetto20242025piragine.db.TransactionDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.List;

public class LastFiveExpensesController extends BranchController
{
    @FXML
    private VBox lastFiveExpensesVBox;




    @Override
    public void initializer()
    {
        for(int i = 0; i < 5; i++)
        {
            try
            {
                List<Transaction> transactions = TransactionDAO.getTransactionsBySender(rootController.user.getUserID());
                FXMLLoader transactionLoader = new FXMLLoader(getClass().getResource("LastFiveExpensesView.fxml"));
                Node transaction = transactionLoader.load();
                TransactionController transactionController = transactionLoader.getController();
                transactionController.SetCorrectValues(transactions.get(i));
                lastFiveExpensesVBox.getChildren().add(i+1, transaction);
            }
            catch (Exception e)
            {
                System.err.println("error loading last five expense widget" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
