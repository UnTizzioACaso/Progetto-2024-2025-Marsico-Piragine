package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.CardController;
import bankapp.progetto20242025piragine.db.BankAccount;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.util.CardService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.math.BigDecimal;

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

    // Creates the card using CardService and user's bank account data
    @FXML
    public void createCard() throws Exception
    {
        BankAccount bankAccount = BankAccountDAO.getAccountByUserId(rootController.user.getUserID()); //getting the user's bank account
        //calling CardService to create the card with nickname, color and spending limit
        CardService.createCard(rootController.user.getUserID(), bankAccount.getIdAccount(), nicknameTextField.getText(), colorMenu.getText(), new BigDecimal(spendingLimitTextField.getText() + ",00"));
    }

    // Updates the card nickname in the preview component as the user types
    @FXML
    public void updateCardExample()
    {
        controller.updateNickname(nicknameTextField.getText());
    }

    // Initializes the popup by loading the card preview component
    @FXML
    public void initialize()
    {
        try
        {
            FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml"));
            cardSlotVbox.getChildren().add(cardLoader.load()); //add the card component to the VBox
            controller = cardLoader.getController(); //get the controller of the card component
            controller.setRootController(rootController); //set the root controller for communication
            controller.removeButtons(); //remove buttons from the preview card
            colorMenu.setText("white"); //set default color menu text
        }
        catch (IOException e)
        {
            System.err.println("error loading the createCardPopup" + e.getMessage());
            e.printStackTrace();
        }
    }

    // Following methods handle color selection and update the card preview accordingly
    @FXML
    public void selectRed()
    {
        colorMenu.setText("Rosso");
        cardSlotVbox.getChildren().getFirst().setStyle("-fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: red;");
    }

    @FXML
    public void selectGreen()
    {
        colorMenu.setText("Verde");
        cardSlotVbox.getChildren().getFirst().setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: green;");
    }

    @FXML
    public void selectBlue()
    {
        colorMenu.setText("Blu");
        cardSlotVbox.getChildren().getFirst().setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: blue;");
    }

    @FXML
    public void selectYellow()
    {
        colorMenu.setText("Giallo");
        cardSlotVbox.getChildren().getFirst().setStyle(" -fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: yellow;");
    }

    @FXML
    public void selectWhite()
    {
        colorMenu.setText("Bianco");
        cardSlotVbox.getChildren().getFirst().setStyle(" -fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: e4e4e4;");
    }
}
