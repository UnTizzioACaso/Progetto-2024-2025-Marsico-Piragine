package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.CardController;
import bankapp.progetto20242025piragine.db.BankAccount;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.db.CardDAO;
import bankapp.progetto20242025piragine.util.CardCreator;
import bankapp.progetto20242025piragine.util.last4DigitsPan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class CreateCardPopupController extends BranchController
{
    // VBox container to hold the card preview component
    @FXML
    public VBox cardSlotVbox;

    // MenuButton to select the card color
    @FXML
    public MenuButton colorMenu;

    // Controller of the card preview component
    public CardController controller;

    // TextField for the card nickname
    @FXML
    public TextField nicknameTextField;

    // TextField for the card spending limit
    @FXML
    public TextField spendingLimitTextField;

    @FXML
    private Label errorLabel;

    private String color = "e4e4e4";

    public Stage s;

    // Creates the card using last4DigitsPan and user's bank account data
    @FXML
    public void createCard() throws Exception
    {

        String spendingLimitText;

        if (spendingLimitTextField.getText().matches("^\\d+(,\\d{1,2})?$")) {spendingLimitText = spendingLimitTextField.getText().replace(",", ".");}
        else if (spendingLimitTextField.getText().matches("^\\d+(.\\d{1,2})?$")) {spendingLimitText =spendingLimitTextField.getText();}
        else {errorLabel.setText("il limite è in formato non valido"); return;}
        {
            try
            {
                BankAccount bankAccount = BankAccountDAO.getAccountByUserId(rootController.user.getUserID());
                try
                {
                    Card card = new Card(rootController.user.getUserID(), bankAccount.getIdAccount(), last4DigitsPan.generateLastFourDigits() ,nicknameTextField.getText(), color, new BigDecimal(spendingLimitText));
                    CardDAO.insertCard(card);
                    s.close();
                    rootController.topbarController.reloadPage();

                }
                catch (SQLException e)
                {
                    System.err.println("error during getting bank account with user id " + e.getMessage());
                    e.printStackTrace();
                }
            }
            catch (SQLException e)
            {
                System.err.println("error during getting bank account with user id " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    // Initializes the popup by loading the card preview component
    @FXML
    public void initializer()
    {
        s = (Stage) colorMenu.getScene().getWindow();
        cardSlotVbox.getChildren().add(CardCreator.cardWithoutButtons(rootController)); //add the card component to the VBox
        colorMenu.setText("Bianco"); //set default color menu text
    }

    // Following methods handle color selection and update the card preview accordingly
    @FXML
    public void selectRed()
    {
        colorMenu.setText("Rosso");
        color = "red";
        cardSlotVbox.getChildren().getFirst().setStyle("-fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: red;");
    }

    @FXML
    public void selectGreen()
    {
        colorMenu.setText("Verde");
        color = "green";
        cardSlotVbox.getChildren().getFirst().setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: green;");
    }

    @FXML
    public void selectBlue()
    {
        colorMenu.setText("Blu");
        color = "blue";
        cardSlotVbox.getChildren().getFirst().setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: blue;");
    }

    @FXML
    public void selectYellow()
    {
        colorMenu.setText("Giallo");
        color = "yellow";
        cardSlotVbox.getChildren().getFirst().setStyle(" -fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: yellow;");
    }

    @FXML
    public void selectWhite()
    {
        colorMenu.setText("Bianco");
        color = "e4e4e4";
        cardSlotVbox.getChildren().getFirst().setStyle(" -fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: e4e4e4;");
    }
}
