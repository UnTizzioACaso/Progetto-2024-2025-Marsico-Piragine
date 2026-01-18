package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Transaction;
import bankapp.progetto20242025piragine.db.TransactionDAO;
import bankapp.progetto20242025piragine.db.UserDAO;
import bankapp.progetto20242025piragine.util.ThemeManager;
import bankapp.progetto20242025piragine.util.VisualTransactionCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


import java.sql.SQLException;
import java.util.List;

public class LargeMonthlyExpensesController extends BranchController {


    @FXML
    public VBox monthlyExpensesVBox;

    @FXML
    public TextField searchTextField;

    @Override
    public void initializer()
    {

        try
        {
            List<Transaction> transactions = TransactionDAO.getCurrentMonthTransactionsBySender(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
            for(Transaction transaction : transactions)
            {
                Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, transaction);
                monthlyExpensesVBox.getChildren().add(visualTransaction);
                ThemeManager.applyTheme(monthlyExpensesVBox.getScene(), rootController.user.getTheme());
            }
        }
        catch (SQLException e)
        {
            System.err.println("error during getting all negative transaction of this month " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void search()
    {
        try
        {
            List<Transaction> transactions = TransactionDAO.getCurrentMonthTransactionsBySender(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
            for(Transaction transaction : transactions)
            {
                try
                {
                    String username = UserDAO.getUsernameByUserId(BankAccountDAO.getUserIdByAccountId(transaction.getBeneficiary()));
                    if(username.contains(searchTextField.getText()))
                    {
                        Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, transaction);
                        monthlyExpensesVBox.getChildren().clear();
                        monthlyExpensesVBox.getChildren().add(visualTransaction);
                        ThemeManager.applyTheme(monthlyExpensesVBox.getScene(), rootController.user.getTheme());
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
