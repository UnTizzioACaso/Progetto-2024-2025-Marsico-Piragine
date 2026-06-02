package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.model.Card;
import bankapp.progetto20242025piragine.dao.CardDAO;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.ThemeManager;
import bankapp.progetto20242025piragine.util.ValueValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;

public class MenageCardPopupController extends BranchController {

    @FXML
    private Button  addFavouritesButton, removeFavouritesButton, blockButton, unblockButton;

    @FXML
    private TextField spendingLimitTextField;

    @FXML
    private Label errorLabelMenageCardPopup;

    private Card card;

    public void setCard(Card card)
    {
        this.card = card;
        this.removeFavouritesButton.setVisible(card.isFavourite());
        this.addFavouritesButton.setVisible(!card.isFavourite());
        this.blockButton.setVisible(card.isStatus());
        this.unblockButton.setVisible(!card.isStatus());
    }

    @FXML
    private void updateSpendingLimit()
    {
        BigDecimal limit = ValueValidator.validateFormat(spendingLimitTextField);
        if (limit == null) {
            errorLabelMenageCardPopup.setText("Formato del limite non valido");
            return;
        }

        BigDecimal maxLimit = new BigDecimal("1000000000.00");

        if (limit.compareTo(maxLimit) > 0) {
            errorLabelMenageCardPopup.setText("Il limite massimo è 1.000.000.000");
            return;
        }

        if (limit.compareTo(BigDecimal.ZERO) <= 0) {
            errorLabelMenageCardPopup.setText("Il limite deve essere maggiore di zero");
            return;
        }

        BigDecimal updatedLimitFromDB = CardDAO.updateCardSpendingLimit(card.getIdCard(), limit);
        card.setSpendingLimit(updatedLimitFromDB);

        spendingLimitTextField.setText(String.valueOf(card.getSpendingLimit()));
        errorLabelMenageCardPopup.setText("");
        CurrentSession.getTopbarController().reloadPage();
    }

    @FXML
    private void addToFavourites()
    {
        card.setFavourite(CardDAO.updateCardFavourite(card.getIdCard(), true));
        addFavouritesButton.setVisible(false);
        removeFavouritesButton.setVisible(true);
        CurrentSession.getTopbarController().reloadPage();
    }

    @FXML
    private void removeFromFavourites()
    {
        card.setFavourite(CardDAO.updateCardFavourite(card.getIdCard(), false));
        addFavouritesButton.setVisible(true);
        removeFavouritesButton.setVisible(false);
        CurrentSession.getTopbarController().reloadPage();
    }

    @FXML
    private void deleteCard()
    {
        CardDAO.deleteCard(card.getIdCard());
        ((Stage) errorLabelMenageCardPopup.getScene().getWindow()).close();
        CurrentSession.getTopbarController().reloadPage();
    }

    @FXML
    private void blockCard()
    {
        CardDAO.updateCardStatus(card.getIdCard(), false);
        ((Stage) errorLabelMenageCardPopup.getScene().getWindow()).close();
        CurrentSession.getTopbarController().reloadPage();
    }

    @FXML
    private void unblockCard()
    {
        CardDAO.updateCardStatus(card.getIdCard(), true);
        ((Stage) errorLabelMenageCardPopup.getScene().getWindow()).close();
        CurrentSession.getTopbarController().reloadPage();
    }

    @FXML
    private void closePopup() {((Stage) errorLabelMenageCardPopup.getScene().getWindow()).close();}


}