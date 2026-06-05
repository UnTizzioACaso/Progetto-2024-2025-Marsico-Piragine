package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.*;
import bankapp.progetto20242025piragine.model.BankAccount;
import bankapp.progetto20242025piragine.model.HomeWidgetCustom;
import bankapp.progetto20242025piragine.model.User;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PasswordUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreatePinPopupController extends BranchController
{
    // Password field where the user inserts the PIN

    @FXML
    private PasswordField insertPinPasswordField, confirmPinPasswordField;

    // Root pane of the popup (used to retrieve the stage)
    @FXML
    private AnchorPane rootPane;

    // Label used to show error messages to the user
    @FXML
    private Label errorMessageLabel;

    private final BigDecimal initialMoney = BigDecimal.valueOf(70.00);

    // Called when the user confirms the PIN creation
    @FXML
    private void completeRegistration()
    {
        // Check if the PIN length is at least 4 characters
        if(insertPinPasswordField.getText().length() < 4) {errorMessageLabel.setText("il pin deve essere lungo almeno 4 cifre");return;}

        // Check if the PIN contains only numeric characters
        if(!(confirmPinPasswordField.getText().matches("^\\d+$"))) {errorMessageLabel.setText("il pin deve essere composto solo da numeri");return;}

        // Check if the two inserted PINs match
        if(!(insertPinPasswordField.getText().equals(confirmPinPasswordField.getText()))) {errorMessageLabel.setText("i pin non coincidono");return;}

        // Hash and set the user's PIN
        CurrentSession.getLoggedUser().setPinHash(PasswordUtil.hashPassword(insertPinPasswordField.getText()));

        // Registration failed
        if (!UserDAO.registerUser(CurrentSession.getLoggedUser())) {errorMessageLabel.setText("Errore durante la registrazione.");return;}

        boolean accountCreated;
        // Try to create the bank account for the newly registered user

        // Reload user from database to retrieve generated ID
        String userEmail = CurrentSession.getLoggedUser().getEmail();
        User user = UserDAO.getUserByEmail(userEmail);

        int id = CurrentSession.getLoggedUser().getUserID();
        // Create a new bank account linked to the user
        BankAccount bankAccount = new BankAccount(id);
        bankAccount.setMoney(initialMoney); //setting initial money
        // Insert the bank account into the database
        accountCreated = BankAccountDAO.insertAccount(bankAccount);
        // If account creation fails, rollback user creation
        if (!accountCreated)
        {
            errorMessageLabel.setText("Errore durante la creazione del conto bancario.");
            Boolean deleted = UserDAO.deleteUserById(CurrentSession.getLoggedUser().getUserID());
            while (!deleted) {deleted = UserDAO.deleteUserById(CurrentSession.getLoggedUser().getUserID());}
            return;
        }

        boolean widgetCreated = false;
        List<String> defaultWidgets = Arrays.asList("lastFiveExpensesVBox", "monthlyBalanceGridPane", "monthlyExpensesVBox", "monthlyIncomesVBox", "quickContactGridPane", "transactionHistoryGridPane");
        List<HomeWidgetCustom> widgets = new ArrayList<>();
        for (String widgetType : defaultWidgets)
        {
            HomeWidgetCustom widget = new HomeWidgetCustom(CurrentSession.getLoggedUser().getUserID(), widgetType);
            widgets.add(widget);
        }
        widgetCreated = HomeWidgetCustomDAO.insertWidgets(widgets);

        if (!widgetCreated)
        {
            errorMessageLabel.setText("Errore durante la creazione del conto bancario.");
            rollbackUser();
            rollbackAccount();
            return;
        }

        CurrentSession.setLoggedUser(user);
        CurrentSession.setLoggedAccount(BankAccountDAO.getAccountByUserId(id));
        // Load the main application pages after successful registration
        CurrentSession.getRootController().loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml");
        CurrentSession.getRootController().loadSideBar();
        CurrentSession.getRootController().loadTopBar();

        // Close the PIN creation popup
        closePopup();
    }

    @FXML
    private void closePopup()
    {
        // Close the PIN creation popup without saving any changes
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void rollbackUser()
    {
        errorMessageLabel.setText("Errore durante la creazione del conto bancario.");
        Boolean deleted = UserDAO.deleteUserById(CurrentSession.getLoggedUser().getUserID());
        while (!deleted) {deleted = UserDAO.deleteUserById(CurrentSession.getLoggedUser().getUserID());}
    }
    private void rollbackAccount()
    {
        errorMessageLabel.setText("Errore durante la creazione del conto bancario.");
        Boolean deleted = BankAccountDAO.deleteAccountById(CurrentSession.getLoggedAccount().getIdAccount());
        while (!deleted) {deleted = BankAccountDAO.deleteAccountById(CurrentSession.getLoggedAccount().getIdAccount());}
    }

}
