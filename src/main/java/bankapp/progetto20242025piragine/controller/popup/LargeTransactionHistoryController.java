package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Transaction;
import bankapp.progetto20242025piragine.db.TransactionDAO;
import bankapp.progetto20242025piragine.db.UserDAO;
import bankapp.progetto20242025piragine.util.VisualTransactionCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LargeTransactionHistoryController extends BranchController {

    @FXML
    private RadioButton expensesRadioButton;

    @FXML
    private RadioButton incomesRadioButton;

    @FXML
    private VBox transactionHistoryVBox;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField searchField;

    private Timestamp fromDate;

    private Timestamp toDate;

    @FXML
    public void filter()
    {
        fixDate();
        if(incomesRadioButton.isSelected()) //positive transactions
        {
            List<Transaction> transactions = getPositiveTransaction();
            for (Transaction t : transactions)
            {
                if(filterBySearchField(t, "expense"))
                {
                    if(t.getTransactionDate().getTime() < toDate.getTime() && fromDate.getTime() < t.getTransactionDate().getTime())
                    {
                        Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, t);
                        transactionHistoryVBox.getChildren().add(visualTransaction);
                    }
                }
            }
        }
        else if (expensesRadioButton.isSelected()) //negative transactions
        {
            List<Transaction> transactions = getNegativeTransactions();
            for (Transaction t : transactions)
            {
                if(filterBySearchField(t, "expense"))
                {
                    if(t.getTransactionDate().getTime() < toDate.getTime() && fromDate.getTime() < t.getTransactionDate().getTime())
                    {
                        Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, t);
                        transactionHistoryVBox.getChildren().add(visualTransaction);
                    }
                }
            }
        }
        else //all transactions
        {
            List<Transaction> transactions = getAllTransactions();
            for (Transaction t : transactions)
            {
                if(filterBySearchField(t, ""))
                {
                    if(t.getTransactionDate().getTime() < toDate.getTime() && fromDate.getTime() < t.getTransactionDate().getTime())
                    {
                        Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, t);
                        transactionHistoryVBox.getChildren().add(visualTransaction);
                    }
                }
            }
        }
    }

    private List<Transaction> getAllTransactions()
    {
        List<Transaction> transactions = new ArrayList<>();
        try
        {
            transactions = TransactionDAO.getAllTransactionsByAccount(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
            return  transactions;
        }
        catch (SQLException e)
        {
            System.err.println("error during getting all transaction " + e.getMessage());
            e.printStackTrace();
            return transactions;
        }
    }

    private List<Transaction> getNegativeTransactions()
    {
        List<Transaction> transactions = new ArrayList<>();
        try
        {
            transactions = TransactionDAO.getTransactionsBySender(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
            return  transactions;
        }
        catch (SQLException e)
        {
            System.err.println("error during getting all transaction " + e.getMessage());
            e.printStackTrace();
            return transactions;
        }
    }

    private List<Transaction> getPositiveTransaction()
    {
        List<Transaction> transactions = new ArrayList<>();
        try
        {
            transactions = TransactionDAO.getTransactionsByBeneficiary(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
            return  transactions;
        }
        catch (SQLException e)
        {
            System.err.println("error during getting all positive transaction " + e.getMessage());
            e.printStackTrace();
            return transactions;
        }
    }

    @FXML
    public void switchIncomes()
    {
        if(expensesRadioButton.isSelected()){expensesRadioButton.setSelected(false);}
        filter();
    }

    @FXML
    public void switchExpenses()
    {
        if (incomesRadioButton.isSelected()){incomesRadioButton.setSelected(false);}
        filter();
    }



    private boolean filterBySearchField(Transaction transaction, String inOrOut) //finds out
    {
        if(searchField.getText().isEmpty()){return true;}
        if(inOrOut.equals("income"))
        {
            try
            {
                return searchField.getText().contains(UserDAO.getUsernameByUserId(BankAccountDAO.getUserIdByAccountId(transaction.getBeneficiary())));
            }
            catch(SQLException e)
            {
                System.err.println("error during finding transaction sender username" + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        else if(inOrOut.equals("expense"))
        {
            try
            {
                return searchField.getText().contains(UserDAO.getUsernameByUserId(BankAccountDAO.getUserIdByAccountId(transaction.getBeneficiary())));
            }
            catch(SQLException e)
            {
                System.err.println("error during finding transaction beneficiary username" + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        else
        {
            try
            {
                return searchField.getText().contains(UserDAO.getUsernameByUserId(BankAccountDAO.getUserIdByAccountId(transaction.getBeneficiary()))) || searchField.getText().contains(UserDAO.getUsernameByUserId(BankAccountDAO.getUserIdByAccountId(transaction.getBeneficiary())));
            }
            catch(SQLException e)
            {
                System.err.println("error during finding transaction beneficiary username" + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }

    }


    private void fixDate()
    {
        if (fromDatePicker.getValue() != null && toDatePicker.getValue() != null) {
            fromDate = Timestamp.valueOf(fromDatePicker.getValue().atStartOfDay());
            toDate   = Timestamp.valueOf(toDatePicker.getValue().atTime(23, 59, 59));
        }
        else if (fromDatePicker.getValue() == null && toDatePicker.getValue() != null) {
            fromDate = Timestamp.valueOf(LocalDateTime.MIN);
            toDate   = Timestamp.valueOf(toDatePicker.getValue().atTime(23, 59, 59));
        }
        else if (fromDatePicker.getValue() != null && toDatePicker.getValue() == null) {
            fromDate = Timestamp.valueOf(fromDatePicker.getValue().atStartOfDay());
            toDate   = Timestamp.valueOf(LocalDateTime.now());
        }
        else {
            fromDate = Timestamp.valueOf(LocalDateTime.MIN);
            toDate   = Timestamp.valueOf(LocalDateTime.now());
        }
    }

    @Override
    public void initializer()
    {
        filter();
    }
}
