package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.CardController;
import bankapp.progetto20242025piragine.model.BankAccount;
import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.model.Card;
import bankapp.progetto20242025piragine.dao.CardDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ThemeManager;
import bankapp.progetto20242025piragine.util.VisualCardCreator;
import bankapp.progetto20242025piragine.util.last4DigitsPan;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;


public class CreateCardPopupController extends BranchController
{
    // VBox container to hold the card preview component
    @FXML
    private VBox cardSlotVbox;

    // MenuButton to select the card color
    @FXML
    private MenuButton colorMenu;

    // TextField for the card nickname
    // TextField for the card spending limit
    @FXML
    private TextField nicknameTextField, spendingLimitTextField;

    @FXML
    private Label errorLabel;

    private String color = "e4e4e4";

    // Creates the card using last4DigitsPan and user's bank account data
    @FXML
    private void createCard()
    {
        String spendingLimitText;

        // format validation
        if (spendingLimitTextField.getText().matches("^\\d+(,\\d{1,2})?$")) {spendingLimitText = spendingLimitTextField.getText().replace(",", ".");}
        else if (spendingLimitTextField.getText().matches("^\\d+(\\.\\d{1,2})?$")) {spendingLimitText = spendingLimitTextField.getText();}
        else {errorLabel.setText("Formato non valido (es: 10,10 o 10.10)");return;}

        BigDecimal limit = new BigDecimal(spendingLimitText);
        BigDecimal maxLimit = new BigDecimal("1000.00");

        // numeric value validation
        if (limit.compareTo(maxLimit) > 0) {
            errorLabel.setText("Il limite massimo è 1.000,00");
            return;
        }
        if (limit.compareTo(BigDecimal.ZERO) < 0) {
            errorLabel.setText("Il limite non può essere negativo");
            return;
        }

        BankAccount bankAccount = BankAccountDAO.getAccountByUserId(CurrentSession.getLoggedUser().getUserID());
        if (bankAccount == null)
        {
            errorLabel.setText("Conto corrente non trovato");
            return;
        }

        Card card = new Card(
                CurrentSession.getLoggedUser().getUserID(),
                bankAccount.getIdAccount(),
                last4DigitsPan.generateLastFourDigits(),
                nicknameTextField.getText(),
                color,
                limit
        );

        if (CardDAO.insertCard(card))
        {
            ((Stage) colorMenu.getScene().getWindow()).close();
            CurrentSession.getTopbarController().reloadPage();
        }
        else {errorLabel.setText("Errore durante il salvataggio della carta");}
    }

    // Initializes the popup by loading the card preview component
    @FXML
    private void initialize()
    {
        cardSlotVbox.getChildren().add(VisualCardCreator.cardWithoutButtons(CurrentSession.getRootController())); //add the card component to the VBox
        colorMenu.setText("Bianco"); //set default color menu text
    }

    // Following methods handle color selection and update the card preview accordingly
    @FXML
    private void selectRed()
    {
        colorMenu.setText("Rosso");
        color = "red";
        cardSlotVbox.getChildren().getFirst().setStyle("-fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: red;");
    }

    @FXML
    private void selectGreen()
    {
        colorMenu.setText("Verde");
        color = "green";
        cardSlotVbox.getChildren().getFirst().setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: green;");
    }

    @FXML
    private void selectBlue()
    {
        colorMenu.setText("Blu");
        color = "blue";
        cardSlotVbox.getChildren().getFirst().setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: blue;");
    }

    @FXML
    private void selectYellow()
    {
        colorMenu.setText("Giallo");
        color = "yellow";
        cardSlotVbox.getChildren().getFirst().setStyle(" -fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: yellow;");
    }

    @FXML
    private void selectWhite()
    {
        colorMenu.setText("Bianco");
        color = "e4e4e4";
        cardSlotVbox.getChildren().getFirst().setStyle(" -fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: e4e4e4;");
    }

    @FXML
    private void closePopup()
    {
        Stage stage = (Stage) colorMenu.getScene().getWindow();
        stage.close();
    }
}
