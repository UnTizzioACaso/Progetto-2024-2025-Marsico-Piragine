package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;

public class AddAccountMoneyPageController extends BranchController
{

        @FXML
        public void confirm()
        {
                PopupCreator.showPopup("", "/bankapp/progetto20242025piragine/fxml/popup/addAccountMoneyPopup.fxml", 400, 500);
        }

        @FXML
        public void payWithApplePay()
        {
                PopupCreator.showPopup("", "/bankapp/progetto20242025piragine/fxml/popup/addAccountMoneyPopup.fxml", 400, 500);
        }

        @FXML
        public void payWithGooglePay()
        {
                PopupCreator.showPopup("", "/bankapp/progetto20242025piragine/fxml/popup/addAccountMoneyPopup.fxml", 400, 500);
        }

}

