package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.CardController;
import bankapp.progetto20242025piragine.db.BankAccount;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.util.CardService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class CreateCardPopupController extends BranchController
{
    @FXML
    public VBox cardSlotVbox;

    @FXML
    public ColorPicker createCardColorPicker;

    @FXML
    public TextField nicknameTextField, spendingLimitTextField;

    @FXML
    public void createCard() throws Exception {
        BankAccount bankAccount = BankAccountDAO.getAccountByUserId(rootController.user.getUserID());
        Card card= CardService.createCard(rootController.user.getUserID(), bankAccount.getIdAccount(), nicknameTextField.getText(), createCardColorPicker.getValue().toString(), new BigDecimal(spendingLimitTextField.getText()+",00"));
    }

    @FXML
    public void initialize()
    {
        try
        {
            FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/bankapp/progetto20242025piragine/fxml/component/card.fxml"));
            cardSlotVbox.getChildren().add(cardLoader.load());
            CardController controller = cardLoader.getController();
            controller.setRootController(rootController);
        }
        catch (IOException e)
        {
            System.err.println("error loading the createCardPopup" + e.getMessage());
            e.printStackTrace();
        }

    }
}
