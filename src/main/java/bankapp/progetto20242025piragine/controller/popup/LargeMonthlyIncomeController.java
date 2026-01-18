package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Transaction;
import bankapp.progetto20242025piragine.db.TransactionDAO;
import bankapp.progetto20242025piragine.db.UserDAO;
import bankapp.progetto20242025piragine.util.VisualTransactionCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.sql.SQLException;
import java.util.List;

public class LargeMonthlyIncomeController extends BranchController {

    @FXML
    public VBox monthlyIncomesVBox;

    @FXML
    public TextField searchTextField;

    @Override
    public void initializer()
    {
        try
        {
            List<Transaction> transactions = TransactionDAO.getCurrentMonthTransactionsByBeneficiary(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
            for(Transaction transaction : transactions)
            {
                Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, transaction);
                monthlyIncomesVBox.getChildren().add(visualTransaction);
            }
        }
        catch (SQLException e)
        {
            System.err.println("error during getting all positive transaction of this month " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void search()
    {

        try
        {
            List<Transaction> transactions = TransactionDAO.getCurrentMonthTransactionsByBeneficiary(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
            for(Transaction transaction : transactions)
            {
                try
                {
                    String username = UserDAO.getUsernameByUserId(BankAccountDAO.getUserIdByAccountId(transaction.getSender()));
                    if(username.contains(searchTextField.getText()))
                    {
                        Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, transaction);
                        monthlyIncomesVBox.getChildren().clear();
                        monthlyIncomesVBox.getChildren().add(visualTransaction);
                    }
                }
                catch (SQLException e)
                {
                    System.err.println("error finding id username by user id got by account id " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("error during getting all positive transaction of this month " + e.getMessage());
            e.printStackTrace();
        }
    }
}
