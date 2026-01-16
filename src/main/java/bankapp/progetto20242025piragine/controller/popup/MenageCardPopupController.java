package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.db.CardDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;

public class MenageCardPopupController extends BranchController {

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
    private Label errorLabel;

    private Stage s;

    public Card card;

    @Override
    public void initializer()
    {
        s = (Stage) errorLabel.getScene().getWindow();
    }
    @FXML
    public void updateSpendingLimit()
    {
        String spendingLimitText;

        if (spendingLimitTextField.getText().matches("^\\d+(,\\d{1,2})?$")) {spendingLimitText = spendingLimitTextField.getText().replace(",", ".");}
        else if (spendingLimitTextField.getText().matches("^\\d+(.\\d{1,2})?$")) {spendingLimitText =spendingLimitTextField.getText();}
        else {errorLabel.setText("il limite è in formato non valido"); return;}
        try
            {
                card.setSpendingLimit(CardDAO.updateCardSpendingLimit(card.getIdCard(), new BigDecimal(spendingLimitText)));
                spendingLimitTextField.setText(String.valueOf(card.getSpendingLimit()));
                rootController.topbarController.reloadPage();
            }
            catch (SQLException e)
            {
                System.out.println("error during updatig card spending limit " + e.getMessage());
                e.printStackTrace();
            }
        }



    @FXML
    public void addToFavourites()
    {
        try
        {
            card.setFavourite(CardDAO.updateCardFavourite(card.getIdCard(), true));
            addFavouritesButton.setVisible(false);
            removeFavouritesButton.setVisible(true);
            rootController.topbarController.reloadPage();
        }
        catch (SQLException e)
        {
            System.out.println("error during updating card favourite state to true" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void removeFromFavourites()
    {
        try
        {
            card.setFavourite(CardDAO.updateCardFavourite(card.getIdCard(), false));
            addFavouritesButton.setVisible(true);
            removeFavouritesButton.setVisible(false);
            rootController.topbarController.reloadPage();
        }
        catch (SQLException e)
        {
            System.out.println("error during updating card favourite state to false" + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    public void deleteCard()
    {
        try
        {
            CardDAO.deleteCard(card.getIdCard());
            s.close();
            rootController.topbarController.reloadPage();
        }
        catch (SQLException e)
        {
            System.out.println("error during updating card favourite state to false" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void blockCard()
    {
        try
        {
            CardDAO.updateCardStatus(card.getIdCard(), false);
            s.close();
            rootController.topbarController.reloadPage();
        }
        catch (SQLException e)
        {
            System.out.println("error during updating card favourite state to false" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void unblockCard()
    {
        try
        {
            CardDAO.updateCardStatus(card.getIdCard(), true);
            s.close();
            rootController.topbarController.reloadPage();
        }
        catch (SQLException e)
        {
            System.out.println("error during updating card status state to true" + e.getMessage());
            e.printStackTrace();
        }
    }

}
