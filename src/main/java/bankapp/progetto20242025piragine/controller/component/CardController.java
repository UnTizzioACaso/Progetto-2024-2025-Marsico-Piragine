package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.MenageCardPopupController;
import bankapp.progetto20242025piragine.dao.CardDAO;
import bankapp.progetto20242025piragine.model.Card;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CardController extends BranchController
{
    @FXML
    private Button cardBlockButton, cardSettingsButton;;

    @FXML
    private Label cardNumberLabel, cardNicknameLabel, cardExpirationLabel;

    @FXML
    private AnchorPane cardBackground;

    private Card card;

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
    private void loadSettings()
    {
        PopupCreator.showAndWaitPopup("inserisci il pin", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
        if(!CurrentSession.isPinCorrect()){return;}
        MenageCardPopupController controller = (MenageCardPopupController) PopupCreator.showPopup("Gestisci la carta", "/bankapp/progetto20242025piragine/fxml/popup/menageCardPopup.fxml", 500, 300);
        controller.setCard(card);
    }


    public void setup(Card c)
    {
        removeButtons();
        this.card = c;
        cardBackground.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: " + c.getColor());
        cardNicknameLabel.setText(c.getNickname());
        cardExpirationLabel.setText(c.getExpired().toString().replaceAll("-", "/").substring(2, 7));
        cardNumberLabel.setText("- - - -  - - - -  - - - -  " + c.getPanLast4());
        if (card.isStatus()) {cardBackground.setOpacity(1);}
        else {cardBackground.setOpacity(0.5);}
    }

    public void setupFavourites(Card c)
    {
        this.card = c;
        cardBackground.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;-fx-background-color: " + c.getColor());
        cardNicknameLabel.setText(c.getNickname());
        cardExpirationLabel.setText(c.getExpired().toString().replaceAll("-", "/").substring(2, 7));
        cardNumberLabel.setText("- - - -  - - - -  - - - -  " + c.getPanLast4());
        if (card.isStatus()) {cardBackground.setOpacity(1);}
        else {cardBackground.setOpacity(0.5);}
    }

    @FXML
    private void blockCard()
    {
        PopupCreator.showAndWaitPopup("inserisci il pin", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190);
        if(!CurrentSession.isPinCorrect()){return;}
        CardDAO.updateCardStatus(card.getIdCard(), !card.isStatus());
        card.setStatus(!card.isStatus());
        cardBlockButton.setText(card.isStatus() ? "Blocca" : "Sblocca");
        if (card.isStatus()) {cardBackground.setOpacity(1);}
        else {cardBackground.setOpacity(0.5);}
    }

}
