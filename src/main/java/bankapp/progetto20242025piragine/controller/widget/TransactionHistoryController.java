package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.dao.TransactionDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.VisualTransactionCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
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

    @FXML
    public void initialize()
    {
        List<Transaction> transactions = TransactionDAO.getAllTransactionsByAccount(BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID()));
        for (Transaction transaction : transactions)
        {
            Node visualTransaction = VisualTransactionCreator.createVisualTransaction(CurrentSession.getRootController(), transaction);
            transactionHistoryVBox.getChildren().add(visualTransaction);
        }
    }

    @FXML
    public void filter()
    {
        populate(transactionHistoryTextField.getText(), Timestamp.valueOf(fromDatePicker.getValue().atStartOfDay()), Timestamp.valueOf(toDatePicker.getValue().atStartOfDay()));
    }

    private void populate(String username, Timestamp from, Timestamp to)
    {
//        List<Transaction> transactions = TransactionDAO.getFilteredTransactionsByAccount(BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID()), username, from, to);
//        for (Transaction transaction : transactions)
//        {
//            Node visualTransaction = VisualTransactionCreator.createVisualTransaction(CurrentSession.getRootController(), transaction);
//            transactionHistoryVBox.getChildren().add(visualTransaction);
//        }
    }








}
