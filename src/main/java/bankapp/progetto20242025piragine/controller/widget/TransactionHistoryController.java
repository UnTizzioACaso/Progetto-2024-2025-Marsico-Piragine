package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.dao.TransactionDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.VisualTransactionCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class TransactionHistoryController extends WidgetController
{
    @FXML
    private GridPane transactionHistoryGridPane;


    @FXML
    private VBox transactionHistoryVBox;

    @Override
    public String getWidgetType(){ return transactionHistoryGridPane.getId();}

    @FXML
    public void showMenu()
    {
        removeWidget();
    }

    @Override
    public void initializer()
    {
        List<Transaction> transactions = TransactionDAO.getAllTransactionsByAccount(BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID()));
        for (Transaction transaction : transactions)
        {
            Node visualTransaction = VisualTransactionCreator.createVisualTransaction(CurrentSession.getRootController(), transaction);
            transactionHistoryVBox.getChildren().add(visualTransaction);
        }
    }



}
