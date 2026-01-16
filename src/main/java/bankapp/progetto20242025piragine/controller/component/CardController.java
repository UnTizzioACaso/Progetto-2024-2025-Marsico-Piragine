package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CardController extends BranchController
{
    @FXML
    public Button cardBlockButton, cardSettingsButton, showCardInformationsButton;

    @FXML
    public Label cardNumberLabel, cardNicknameLabel, cardCreditLabel, cardExpirationLabel;

    @FXML
    public AnchorPane cardBackground;

    public void removeButtons()
    {
        cardBlockButton.setVisible(false);
        cardSettingsButton.setVisible(false);
        showCardInformationsButton.setVisible(false);
    }

    public void updateNickname(String nickname)
    {
        cardNicknameLabel.setText(nickname);
    }

    public void setup(Card c)
    {
        removeButtons();
        cardBackground.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: " + c.getColor());
        cardNicknameLabel.setText(c.getNickname());
        cardExpirationLabel.setText(c.getExpired().toString());
        cardNumberLabel.setText("- - - -  - - - -  - - - -  " + c.getPanLast4());
    }
    public void getNickname()
    {

    }

    public void setupFavourites(Card c)
    {
        cardBackground.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: " + c.getColor());
        cardNicknameLabel.setText(c.getNickname());
        cardExpirationLabel.setText(c.getExpired().toString());
        cardNumberLabel.setText("- - - -  - - - -  - - - -  " + c.getPanLast4());
    }

}
