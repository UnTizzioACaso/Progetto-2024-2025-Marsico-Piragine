package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.Transaction;
import bankapp.progetto20242025piragine.db.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class TransactionController extends BranchController
{
    @FXML
    private Label expenseBeneficiaryLabel, expenseDateLabel, expanseValueLabel;


    public void SetCorrectValues(Transaction transaction) {
        expanseValueLabel.setText("-" + transaction.getAmmount().toString() + " €");
        expenseDateLabel.setText(transaction.getTransactionDate().toString());
        try
        {
            String name = UserDAO.getUserByUserID(transaction.getBeneficiary().toString()).getUsername();
            expenseBeneficiaryLabel.setText(name);
        }
        catch (Exception e)
        {
            System.err.println("error loading last five expense widget" + e.getMessage());
            e.printStackTrace();
        }
    }
}
