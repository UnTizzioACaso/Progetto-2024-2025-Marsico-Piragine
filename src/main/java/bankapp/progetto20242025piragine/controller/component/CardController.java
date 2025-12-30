package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CardController extends BranchController
{
    @FXML
    Button cardBlockButton, cardSettingsButton, showCardInformationsButton;

    @Override
    public void initializer()
    {
        cardBlockButton.setVisible(false);
        cardBlockButton.setDisable(true);
        cardSettingsButton.setVisible(false);
        cardSettingsButton.setDisable(true);
        showCardInformationsButton.setVisible(false);
        showCardInformationsButton.setDisable(true);
    }

}
