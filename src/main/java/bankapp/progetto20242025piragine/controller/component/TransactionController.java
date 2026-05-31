package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.dao.UserDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;


public class TransactionController extends BranchController
{
    @FXML
    private Label transactionSubjectLabel, TransactionDateLabel, transactionValueLabel;


    public void setCorrectValues(Transaction transaction)
    {
        int id = 0;
        id = BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID());

        if (transaction.getBeneficiary() == id) //positive transactions
        {
            try
            {
                String username = UserDAO.getUsernameByUserId(BankAccountDAO.getUserIdByAccountId(transaction.getSender()));
                transactionValueLabel.setText("+" + transaction.getAmount().toString() + " €");
                transactionSubjectLabel.setText(username);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String cleanDate = transaction.getTransactionDate().toLocalDateTime().format(formatter);
                TransactionDateLabel.setText(cleanDate);
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");;
                String cleanDate = transaction.getTransactionDate().toLocalDateTime().format(formatter);
                TransactionDateLabel.setText(cleanDate);
            }
            catch (SQLException e)
            {
                System.err.println("error finding id username by user id got by account id " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
