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
    @FXML
    public VBox cardSlotVbox;

    @FXML
    public MenuButton colorMenu;

    public CardController controller;

    @FXML
    public TextField nicknameTextField, spendingLimitTextField;

    @FXML
    public void createCard() throws Exception
    {
        BankAccount bankAccount = BankAccountDAO.getAccountByUserId(rootController.user.getUserID());
        CardService.createCard(rootController.user.getUserID(), bankAccount.getIdAccount(), nicknameTextField.getText(), colorMenu.getText(), new BigDecimal(spendingLimitTextField.getText()+",00"));
    }

    @FXML
    public void updateCardExample()
    {
        controller.updateNickname(nicknameTextField.getText());
    }



    @FXML
    public void initialize()
    {
        try
        {
            FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml"));
            cardSlotVbox.getChildren().add(cardLoader.load());
            controller = cardLoader.getController();
            controller.setRootController(rootController);
            controller.removeButtons();
            colorMenu.setText("white");

        }
        catch (IOException e)
        {
            System.err.println("error loading the createCardPopup" + e.getMessage());
            e.printStackTrace();
        }
    }

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
