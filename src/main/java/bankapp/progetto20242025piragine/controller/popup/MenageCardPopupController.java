package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.db.CardDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.sql.SQLException;

public class MenageCardPopupController extends BranchController {

    @FXML
    private Button addFavouritesButton;

    @FXML
    private Button removeFavouritesButton;

    @FXML
    private Button blockButton;

    @FXML
    private Button unblockButton;

    @FXML
    private TextField spendingLimitTextField;

    @FXML
    private Label errorLabel;

    public Card card;

    @FXML
    public void updateSpendingLimit()
    {
        if (spendingLimitTextField.getText().matches("^\\d+(\\.\\d+)?$"))
        {
            BigDecimal spendingLimit = new BigDecimal(spendingLimitTextField.getText());
            try {card.setSpendingLimit(CardDAO.updateCardSpendingLimit(card.getIdCard(), spendingLimit));}
            catch (SQLException e)
            {
                System.out.println("error during updatig card spending limit " + e.getMessage());
                e.printStackTrace();
            }
        }
        else {errorLabel.setText("il limite è in formato non valido");}
    }

    @FXML
    public void addToFavourites()
    {
        try {card.setFavourite(CardDAO.updateCardFavourite(card.getIdCard(), true));}
        catch (SQLException e)
        {
            System.out.println("error during updating card favourite state to true" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void removeFromFavourites()
    {
        try {card.setFavourite(CardDAO.updateCardFavourite(card.getIdCard(), false));}
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

        }
        catch (SQLException e)
        {
            System.out.println("error during updating card favourite state to false" + e.getMessage());
            e.printStackTrace();
        }
    }
}
