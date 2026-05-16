package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.dao.TransactionDAO;
import bankapp.progetto20242025piragine.dao.UserDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
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
        List<Transaction> transactions = TransactionDAO.getCurrentMonthOutcome(BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID()));
        for(Transaction transaction : transactions)
        {
            Node visualTransaction = VisualTransactionCreator.createVisualTransaction(CurrentSession.getRootController(), transaction);
            monthlyExpensesVBox.getChildren().add(visualTransaction);
            ThemeManager.applyTheme(monthlyExpensesVBox.getScene(), CurrentSession.getLoggedUser().getTheme());
        }
    }

    @FXML
    public void search()
    {
        List<Transaction> transactions = TransactionDAO.getCurrentMonthOutcome(BankAccountDAO.getIdAccountByUserId(CurrentSession.getLoggedUser().getUserID()));
        for (Transaction transaction : transactions) {
            try {
                String username = UserDAO.getUsernameByUserId(BankAccountDAO.getUserIdByAccountId(transaction.getBeneficiary()));
                if (username.contains(searchTextField.getText())) {
                    Node visualTransaction = VisualTransactionCreator.createVisualTransaction(CurrentSession.getRootController(), transaction);
                    monthlyExpensesVBox.getChildren().clear();
                    monthlyExpensesVBox.getChildren().add(visualTransaction);
                    ThemeManager.applyTheme(monthlyExpensesVBox.getScene(), CurrentSession.getLoggedUser().getTheme());
                }
            } catch (SQLException e) {
                System.err.println("error finding id username by user id got by account id " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
