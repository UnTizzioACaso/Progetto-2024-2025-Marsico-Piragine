package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.*;
import bankapp.progetto20242025piragine.util.PasswordUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CreatePinPopupController extends BranchController
{
    // Password field where the user inserts the PIN
    @FXML
    public PasswordField insertPinPasswordField, confirmPinPasswordField;

    // Root pane of the popup (used to retrieve the stage)
    @FXML
    public AnchorPane rootPane;

    // Label used to show error messages to the user
    @FXML
    public Label errorMessageLabel;

    // Called when the user confirms the PIN creation
    @FXML
    public void completeRegistration()
    {

        // Check if the PIN length is at least 4 characters
        if(insertPinPasswordField.getText().length() < 4)
        {
            errorMessageLabel.setText("il pin deve essere lungo almeno 4 cifre");
            return;
        }

        // Check if the PIN contains only numeric characters
        if(!(confirmPinPasswordField.getText().matches("^\\d+$")))
        {
            errorMessageLabel.setText("il pin deve essere composto solo da numeri");
            return;
        }

        // Check if the two inserted PINs match
        if(!(insertPinPasswordField.getText().equals(confirmPinPasswordField.getText())))
        {
            errorMessageLabel.setText("i pin non coincidono");
            return;
        }

        // Hash and set the user's PIN
        rootController.user.setPinHash(PasswordUtil.hashPassword(insertPinPasswordField.getText()));

        // Try to register the user in the database
        try
        {
            boolean result = UserDAO.registerUser(rootController.user);// Insert user into DB
            if (!result) // Registration failed
            {
                errorMessageLabel.setText("Errore durante la registrazione.");
                return;
            }
        }
        catch (Exception e)
        {
            // Handle any exception during user registration
            System.err.println("error registering user: " + e.getMessage());
            e.printStackTrace();
        }
        boolean accountCreated;
        // Try to create the bank account for the newly registered user
        try
        {


            // Reload user from database to retrieve generated ID
            String userEmail = rootController.user.getEmail();
            rootController.user = UserDAO.getUserByEmail(userEmail);
            int id = rootController.user.getUserID();

            // Create a new bank account linked to the user
            BankAccount bankAccount = new BankAccount(id);
            bankAccount.setUserId(id);

            // Insert the bank account into the database
            accountCreated = BankAccountDAO.insertAccount(bankAccount);

            // If account creation fails, rollback user creation
            if (!accountCreated)
            {
                errorMessageLabel.setText("Errore durante la creazione del conto bancario.");
                UserDAO.deleteUserById(rootController.user.getUserID());
                return;
            }



        }
        catch (Exception e)
        {
            // Handle any exception during bank account creation
            System.err.println("error creating the bank account: " + e.getMessage());
            e.printStackTrace();
        }
        accountCreated = false;
        try
        {
            HomeWidgetCustom widgetCustom = new HomeWidgetCustom(rootController.user.getUserID(), "lastFiveExpensesVBox");
            accountCreated = HomeWidgetCustomDAO.insertWidget(widgetCustom);
            if (!accountCreated)
            {
                errorMessageLabel.setText("Errore durante la creazione del conto bancario.");
                UserDAO.deleteUserById(rootController.user.getUserID());
                return;
            }
            System.out.println("widget aggiunto");
            accountCreated = false;
            widgetCustom = new HomeWidgetCustom(rootController.user.getUserID(), "monthlyBalanceGridPane");
            accountCreated = HomeWidgetCustomDAO.insertWidget(widgetCustom);
            if (!accountCreated)
            {
                errorMessageLabel.setText("Errore durante la creazione del conto bancario.");
                UserDAO.deleteUserById(rootController.user.getUserID());
                return;
            }
            System.out.println("widget aggiunto");
            accountCreated = false;
            widgetCustom = new HomeWidgetCustom(rootController.user.getUserID(), "monthlyExpensesVBox");
            accountCreated = HomeWidgetCustomDAO.insertWidget(widgetCustom);
            if (!accountCreated)
            {
                errorMessageLabel.setText("Errore durante la creazione del conto bancario.");
                UserDAO.deleteUserById(rootController.user.getUserID());
                return;
            }
            System.out.println("widget aggiunto");
            accountCreated = false;
            widgetCustom = new HomeWidgetCustom(rootController.user.getUserID(), "monthlyIncomesVBox");
            accountCreated = HomeWidgetCustomDAO.insertWidget(widgetCustom);
            if (!accountCreated)
            {
                errorMessageLabel.setText("Errore durante la creazione del conto bancario.");
                UserDAO.deleteUserById(rootController.user.getUserID());
                return;
            }
            System.out.println("widget aggiunto");
            accountCreated = false;
            widgetCustom = new HomeWidgetCustom(rootController.user.getUserID(), "quickContactGridPane");
            accountCreated = HomeWidgetCustomDAO.insertWidget(widgetCustom);
            if (!accountCreated)
            {
                errorMessageLabel.setText("Errore durante la creazione del conto bancario.");
                UserDAO.deleteUserById(rootController.user.getUserID());
                return;
            }
            System.out.println("widget aggiunto");
            accountCreated = false;
            widgetCustom = new HomeWidgetCustom(rootController.user.getUserID(), "transactionHistoryGridPane");
            accountCreated = HomeWidgetCustomDAO.insertWidget(widgetCustom);
            if (!accountCreated)
            {
                errorMessageLabel.setText("Errore durante la creazione del conto bancario.");
                UserDAO.deleteUserById(rootController.user.getUserID());
                return;
            }
            System.out.println("widget aggiunto");
        }
        catch (SQLException e)
        {
            System.err.println("error adding widgets to the account: " + e.getMessage());
        }
        // Load the main application pages after successful registration
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml");
        rootController.loadSideBar("/bankapp/progetto20242025piragine/fxml/component/sidebar.fxml");
        rootController.loadTopBar("/bankapp/progetto20242025piragine/fxml/component/topbar.fxml");

        // Close the PIN creation popup
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
