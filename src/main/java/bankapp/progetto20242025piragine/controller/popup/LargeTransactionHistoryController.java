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

    @FXML
    public void filter()
    {

        String fromDateString = fromDatePicker.getValue().toString().replace("/", "-");
        Timestamp fromDate =  Timestamp.valueOf(fromDateString);
        String toDateString = toDatePicker.getValue().toString().replace("/", "-");
        Timestamp toDate =  Timestamp.valueOf(toDateString);
        try
        {
            List<Transaction> transactions = TransactionDAO.getAllTransactionsByAccount(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID()));
            transactionHistoryVBox.getChildren().clear();
            for(Transaction transaction : transactions)
            {
                try
                {
                    if(incomesRadioButton.isSelected() && transaction.getBeneficiary().equals(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID())))
                    {
                        if(fromDate.getTime() < toDate.getTime() && fromDate.getTime() < transaction.getTransactionDate().getTime() && transaction.getIdTransaction() < toDate.getTime())
                        {
                            if(!searchField.getText().isEmpty())
                            {
                                Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, transaction);
                                transactionHistoryVBox.getChildren().add(visualTransaction);
                            }
                            else
                            {
                                try
                                {
                                    if (searchField.getText().contains(UserDAO.getUsernameByUserId(BankAccountDAO.getUserIdByAccountId(transaction.getSender()))))
                                    {
                                        Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, transaction);
                                        transactionHistoryVBox.getChildren().add(visualTransaction);
                                    }
                                }
                                catch(SQLException e)
                                {
                                    System.err.println("error during finding transaction sender username" + e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                        }
                        else {errorLabel.setText("La data di inizio deve essere piu vecchia di quella di fine"); return;}

                    }
                    else if (expensesRadioButton.isSelected() && transaction.getSender().equals(BankAccountDAO.getIdAccountByUserId(rootController.user.getUserID())))
                    {
                        Node visualTransaction = VisualTransactionCreator.createVisualTransaction(rootController, transaction);
                        transactionHistoryVBox.getChildren().add(visualTransaction);
                    }
                }
                catch (SQLException e)
                {
                    System.err.println("error during getting user's bank account id " + e.getMessage());
                    e.printStackTrace();
                }


            }
        }
        catch (SQLException e)
        {
            System.err.println("error during getting all transaction " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void switchExpensesIncomes()
    {
        if(expensesRadioButton.isSelected())
        {
            incomesRadioButton.setSelected(false);
            filter();
        }
        else if (incomesRadioButton.isSelected())
        {
            expensesRadioButton.setSelected(false);
            filter();
        }
    }

    private void filterBy()
    {

    }

    @Override
    public void initializer() {

    }
}
