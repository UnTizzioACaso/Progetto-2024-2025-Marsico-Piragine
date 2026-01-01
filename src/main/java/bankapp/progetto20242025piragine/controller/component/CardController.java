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
    public Label cardNumberLabel, cardNicknameLabel, cardCreditLabel, cardCvvLabel, cardExpirationLabel;

    @FXML
    public AnchorPane cardBackground;

    public void setup(Card c)
    {
        cardBlockButton.setVisible(false);
        cardBlockButton.setDisable(true);
        cardSettingsButton.setVisible(false);
        cardSettingsButton.setDisable(true);
        cardBackground.setStyle("-fx-background-color: " + c.getColour());
        cardNicknameLabel.setText(c.getNickname());
        cardExpirationLabel.setText(c.getExpired().toString());
        cardNumberLabel.setText("- - - -  - - - -  - - - -  " + c.getPanLast4());
    }

}
