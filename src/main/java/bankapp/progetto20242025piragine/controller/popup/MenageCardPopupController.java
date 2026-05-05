package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.BankAccount;
import bankapp.progetto20242025piragine.db.BankAccountDAO;
import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.db.CardDAO;
import bankapp.progetto20242025piragine.util.ThemeManager;
import bankapp.progetto20242025piragine.util.last4DigitsPan;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;

public class MenageCardPopupController extends BranchController {

    @FXML
    public Button closeMenageCardPopup;
    @FXML
    public Button addFavouritesButton;

    @FXML
    public Button removeFavouritesButton;

    @FXML
    public Button blockButton;

    @FXML
    public Button unblockButton;

    @FXML
    private TextField spendingLimitTextField;

    @FXML
    private Label errorLabelMenageCardPopup;

    private Stage s;

    public Card card;

    @Override
    public void initializer() {
        s = (Stage) errorLabelMenageCardPopup.getScene().getWindow();
        ThemeManager.applyTheme(s.getScene(), rootController.user.getTheme());
    }

    @FXML
    public void updateSpendingLimit() {
        String spendingLimitText;
        String input = spendingLimitTextField.getText().trim();

        // 1. Validazione del formato (Regex)
        if (input.matches("^\\d+(,\\d{1,2})?$")) {
            spendingLimitText = input.replace(",", ".");
        } else if (input.matches("^\\d+(\\.\\d{1,2})?$")) {
            spendingLimitText = input;
        } else {
            errorLabelMenageCardPopup.setText("Formato non valido (es: 10,10 o 10.10)");
            return;
        }

        try {
            BigDecimal limit = new BigDecimal(spendingLimitText);
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

            if (rootController.topbarController != null) {
                rootController.topbarController.reloadPage();
            }

        } catch (NumberFormatException e) {
            errorLabelMenageCardPopup.setText("Errore nella conversione del numero");
        } catch (Exception e) {
            errorLabelMenageCardPopup.setText("Errore durante l'aggiornamento");
            e.printStackTrace();
        }
    }

    @FXML
    public void addToFavourites() {
        try {
            card.setFavourite(CardDAO.updateCardFavourite(card.getIdCard(), true));
            addFavouritesButton.setVisible(false);
            removeFavouritesButton.setVisible(true);
            rootController.topbarController.reloadPage();
        } catch (SQLException e) {
            System.out.println("error during updating card favourite state to true" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void removeFromFavourites() {
        try {
            card.setFavourite(CardDAO.updateCardFavourite(card.getIdCard(), false));
            addFavouritesButton.setVisible(true);
            removeFavouritesButton.setVisible(false);
            rootController.topbarController.reloadPage();
        } catch (SQLException e) {
            System.out.println("error during updating card favourite state to false" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteCard() {
        try {
            CardDAO.deleteCard(card.getIdCard());
            s.close();
            rootController.topbarController.reloadPage();
        } catch (SQLException e) {
            System.out.println("error during deleting card" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void blockCard() {
        try {
            CardDAO.updateCardStatus(card.getIdCard(), false);
            s.close();
            rootController.topbarController.reloadPage();
        } catch (SQLException e) {
            System.out.println("error during blocking card" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void unblockCard() {
        try {
            CardDAO.updateCardStatus(card.getIdCard(), true);
            s.close();
            rootController.topbarController.reloadPage();
        } catch (SQLException e) {
            System.out.println("error during updating card status state to true" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void closePopup() {
        s.close();
    }
}