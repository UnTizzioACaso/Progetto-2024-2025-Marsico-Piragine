package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.dao.TransactionDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.math.BigDecimal;
import java.util.List;

public class MonthlyBalanceController extends WidgetController {

    @FXML
    private GridPane monthlyBalanceGridPane;

    @FXML
    private Label incomesLabel;

    @FXML
    private VBox balanceVBox;

    @FXML
    private Label expensesLabel;

    @FXML
    private Label balanceLabel;



    @Override
    public String getWidgetType()
    {
        return monthlyBalanceGridPane.getId();
    }

    @Override
    public void initializer()
    {
            List<Transaction> positiveTransaction = TransactionDAO.getCurrentMonthIncome(BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID()));
            List<Transaction> negativeTransaction = TransactionDAO.getCurrentMonthOutcome(BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID()));
            BigDecimal positiveAmount = BigDecimal.ZERO;
            BigDecimal negativeAmount = BigDecimal.ZERO;
            for(Transaction transaction : positiveTransaction)
            {
                positiveAmount = positiveAmount.add(transaction.getAmount());
            }
            for(Transaction transaction : negativeTransaction)
            {
                negativeAmount = negativeAmount.add(transaction.getAmount());
            }
            BigDecimal balance = positiveAmount.subtract(negativeAmount);
            incomesLabel.setText("+" + positiveAmount + " €");
            expensesLabel.setText("-" + negativeAmount + " €");
            balanceLabel.setText("€" + balance);
            if (balance.compareTo(BigDecimal.ZERO) < 0) {
                balanceVBox.setStyle(balanceVBox.getStyle() + "-fx-background-color: red;");}
            else if (balance.compareTo(BigDecimal.ZERO) > 0) {balanceVBox.setStyle(balanceVBox.getStyle() + "-fx-background-color: green;");}
            else {balanceVBox.setStyle(balanceVBox.getStyle() + "-fx-background-color: #bfb600;");}


    }

    @FXML
    public void showMenu()
    {
        removeWidget();
    }

}
