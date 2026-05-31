package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.dao.TransactionDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.VisualTransactionCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


import java.sql.Timestamp;
import java.util.List;

public class TransactionHistoryController extends WidgetController
{
    @FXML
    private GridPane transactionHistoryGridPane;

    @FXML
    private TextField transactionHistoryTextField;

    @FXML
    private DatePicker fromDatePicker, toDatePicker;

    @FXML
    private VBox transactionHistoryVBox;

    @Override
    public String getWidgetType(){ return transactionHistoryGridPane.getId();}

    @FXML
    public void showMenu()
    {
        removeWidget();
    }

    private List<Transaction> transactions;

    @FXML
    public void initialize()
    {
        transactions = TransactionDAO.getAllTransactionsByAccount(BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID())).reversed();
        for (Transaction transaction : transactions)
        {
            Node visualTransaction = VisualTransactionCreator.createVisualTransaction(transaction);
            transactionHistoryVBox.getChildren().add(visualTransaction);
        }
    }

    @FXML
    public void filter()
    {
        populate(transactionHistoryTextField.getText(), Timestamp.valueOf(fromDatePicker.getValue().atStartOfDay()), Timestamp.valueOf(toDatePicker.getValue().atStartOfDay()));
    }

    private void populate(String name, Timestamp from, Timestamp to) {
        transactionHistoryVBox.getChildren().clear();

        for (Transaction transaction : transactions)
        {
            GridPane visualTransaction = (GridPane) VisualTransactionCreator.createVisualTransaction(transaction);

            boolean dateOk = !transaction.getTransactionDate().before(from) && transaction.getTransactionDate().getTime() <= (to.getTime() + 86399999L);
            Label usernameLabel = (Label) visualTransaction.getChildren().getFirst();
            boolean userOk = usernameLabel.getText().toLowerCase().contains(name.toLowerCase());
            if (dateOk && userOk)
            {
                transactionHistoryVBox.getChildren().add(visualTransaction);
            }
        }
    }






}
