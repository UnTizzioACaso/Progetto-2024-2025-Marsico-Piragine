package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Transaction;
import bankapp.progetto20242025piragine.db.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

import java.sql.SQLException;


public class TransactionController extends BranchController
{
    @FXML
    private Label transactionSubjectLabel, TransactionDateLabel, transactionValueLabel;


    public void SetCorrectValues(Transaction transaction)
    {
        int id = 0;
        try {id = BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID());}
        catch (SQLException e)
        {
            System.err.println("error finding id account by user id " + e.getMessage());
            e.printStackTrace();
            return;
        }
        if (transaction.getBeneficiary() == id) //positive transactions
        {
            try
            {
                String username = UserDAO.getUsernameByUserId(BankAccountDAO.getUserIdByAccountId(transaction.getSender()));
                transactionValueLabel.setText("+" + transaction.getAmount().toString() + " €");
                transactionValueLabel.setTextFill(Paint.valueOf("green"));
                transactionSubjectLabel.setText(username);
                TransactionDateLabel.setText(transaction.getTransactionDate().toString());
            }
            catch (SQLException e)
            {
                System.err.println("error finding id username by user id got by account id " + e.getMessage());
                e.printStackTrace();
            }
        }
        else //negative transactions
        {
            try
            {
                String username = UserDAO.getUsernameByUserId(BankAccountDAO.getUserIdByAccountId(transaction.getBeneficiary()));
                transactionValueLabel.setText("-" + transaction.getAmount().toString() + " €");
                transactionValueLabel.setTextFill(Paint.valueOf("red"));
                transactionSubjectLabel.setText(username);
                TransactionDateLabel.setText(transaction.getTransactionDate().toString());
            }
            catch (SQLException e)
            {
                System.err.println("error finding id username by user id got by account id " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
