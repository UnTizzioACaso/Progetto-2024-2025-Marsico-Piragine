package bankapp.progetto20242025piragine.util;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.RootWindowController;
import bankapp.progetto20242025piragine.controller.component.TransactionController;
import bankapp.progetto20242025piragine.db.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class VisualTransactionCreator {

    public static Node createVisualTransaction(RootWindowController rootController, Transaction transaction)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(VisualTransactionCreator.class.getResource("/bankapp/progetto20242025piragine/fxml/component/transaction.fxml"));
            Node visualTransaction = fxmlLoader.load();
            TransactionController controller = fxmlLoader.getController();
            controller.setRootController(rootController);
            controller.setCorrectValues(transaction);
            return visualTransaction;
        }
        catch(Exception e)
        {
            System.err.println("error during creating a transaction " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
