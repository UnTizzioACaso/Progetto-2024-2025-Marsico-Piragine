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

public class LastFiveExpensesController extends WidgetController
{
    // VBox container that will hold the last five expense transactions
    @FXML
    private VBox lastFiveExpensesVBox;

    // Initializes the widget and loads the last five expenses of the current user
    @Override
    public void initializer()
    {
        for(int i = 0; i < 5; i++) // Loop to load up to 5 transactions
        {
            try
            {
                // Fetch all transactions sent by the current user
                List<Transaction> transactions = TransactionDAO.getTransactionsBySender(rootController.user.getUserID());

                // Load the FXML for a single transaction row
                FXMLLoader transactionLoader = new FXMLLoader(getClass().getResource("LastFiveExpensesView.fxml"));
                Node transaction = transactionLoader.load(); // Create the node from the FXML

                // Get the controller for this transaction and set its values
                TransactionController transactionController = transactionLoader.getController();
                transactionController.SetCorrectValues(transactions.get(i)); // Fill transaction data

                // Add the transaction node to the VBox at position i+1
                lastFiveExpensesVBox.getChildren().add(i+1, transaction);
            }
            catch (Exception e)
            {
                // Print error if loading any transaction fails
                System.err.println("Error loading last five expense widget: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
