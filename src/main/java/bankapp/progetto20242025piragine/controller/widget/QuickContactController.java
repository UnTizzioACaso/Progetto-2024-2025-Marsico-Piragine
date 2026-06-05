package bankapp.progetto20242025piragine.controller.widget;

import bankapp.progetto20242025piragine.dao.BankAccountDAO;
import bankapp.progetto20242025piragine.dao.UserDAO;
import bankapp.progetto20242025piragine.model.BankAccount;
import bankapp.progetto20242025piragine.model.User;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import bankapp.progetto20242025piragine.util.ValueValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.math.BigDecimal;

public class QuickContactController extends WidgetController {
    @FXML
    private GridPane quickContactGridPane;

    @FXML
    private Label quickContactNameLabel;

    @FXML
    private TextField valueFiled;

    @FXML
    private void showMenu() {
        removeWidget();
    }

    @Override
    public String getWidgetType() {
        return quickContactGridPane.getId();
    }

    private void sendRequest()
    {
        PopupCreator.showAndWaitPopup("inserisci un pin", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
        if (!CurrentSession.isPinCorrect()) {return;}
        BigDecimal value = ValueValidator.validateFormat(valueFiled.getText());
        if (value == null) {return;}
        if (value.compareTo(new BigDecimal("100,00")) > 0) {return;}


    }

    private void sendDonation() {
        PopupCreator.showAndWaitPopup("inserisci un pin", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
        if (!CurrentSession.isPinCorrect()) {return;}
        BigDecimal value = ValueValidator.validateFormat(valueFiled.getText());
        if (value == null) {return;}
        if (value.compareTo(CurrentSession.getLoggedAccount().getMaxTransfer()) > 0) {return;}
        if (value.compareTo(CurrentSession.getLoggedAccount().getMoney()) > 0) {return;}
        User friend = UserDAO.getUserByUsername(quickContactNameLabel.getText());
        if (friend == null) {return;}
        BankAccount friendAccount = BankAccountDAO.getAccountByUserId(friend.getUserID());
        if (friendAccount == null) {return;}
        if (value.add(friendAccount.getMoney()).compareTo(new BigDecimal("9223372036854775807,00")) > 0){return;}


    }
}