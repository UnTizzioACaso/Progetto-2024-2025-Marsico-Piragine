package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.MenageCardPopupController;
import bankapp.progetto20242025piragine.model.Card;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CardController extends BranchController
{
    @FXML
    public Button cardBlockButton, cardSettingsButton;

    @FXML
    public Label cardNumberLabel, cardNicknameLabel, cardExpirationLabel;

    @FXML
    public AnchorPane cardBackground;

    public Card card;

    public void removeButtons()
    {
        cardBlockButton.setVisible(false);
        cardSettingsButton.setVisible(false);
    }

    public void updateNickname(String nickname)
    {
        cardNicknameLabel.setText(nickname);
    }

    @FXML
    public void loadSettings()
    {
        MenageCardPopupController controller = (MenageCardPopupController) PopupCreator.showPopup("Gestisci la carta", "/bankapp/progetto20242025piragine/fxml/popup/menageCardPopup.fxml", 500, 300);
        controller.card = card;
        controller.removeFavouritesButton.setVisible(card.isFavourite());
        controller.addFavouritesButton.setVisible(!card.isFavourite());
        controller.blockButton.setVisible(card.isStatus());
        controller.unblockButton.setVisible(!card.isStatus());
    }


    public void setup(Card c)
    {
        removeButtons();
        cardBackground.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: " + c.getColor());
        cardNicknameLabel.setText(c.getNickname());
        cardExpirationLabel.setText(c.getExpired().toString().replaceAll("-", "/").substring(2, 7));
        cardNumberLabel.setText("- - - -  - - - -  - - - -  " + c.getPanLast4());
    }

    public void setupFavourites(Card c)
    {
        this.card = c;
        cardBackground.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: " + c.getColor());
        cardNicknameLabel.setText(c.getNickname());
        cardExpirationLabel.setText(c.getExpired().toString().replaceAll("-", "/").substring(2, 7));
        cardNumberLabel.setText("- - - -  - - - -  - - - -  " + c.getPanLast4());
    }

}
