package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.dao.*;
import bankapp.progetto20242025piragine.model.Notify;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PaymentRequestController extends BranchController {

    public Transaction request;

    public Notify n;



    @FXML
    public Label friendshipUsernameLabel;

    @FXML
    public Label moneyLabel;

    @FXML
    public void declineRequest()
    {
        if (getPinPopupController() != null){getPinPopupController().abort();} //if pin popup has been generated, we close it it
        BlockUserPopupController controller = (BlockUserPopupController) PopupCreator.showPopup("Blocca un utente", "/bankapp/progetto20242025piragine/fxml/popup/blockUserPopup.fxml", 420, 300);
        controller.wouldYouLikeToBlockLabel.setText("Vorresti bloccare " + friendshipUsernameLabel.getText() + "?");
        controller.username = friendshipUsernameLabel.getText();
        if(n != null)
        {
            if (TransactionDAO.declineTransacion(n.getIdTransaction()))
            {

                NotifyDAO.markAsRead(n.getIdNotify());
                ((Stage) moneyLabel.getScene().getWindow()).close();
                return;
            }
        }
        CurrentSession.getFriendsPageController().currentFriendController.showChat();
        TransactionDAO.declineTransacion(request.getIdTransaction());
        ((Stage) moneyLabel.getScene().getWindow()).close();
    }

    @FXML
    public void acceptRequest()
    {
        /* if pinPopupController is not null, it means that the user has already tried to accept the request and has entered the PIN,
         so we check if the PIN is correct and if it is, we accept the request, otherwise we show the PIN popup again */
        if (getPinPopupController() != null)
        {
            if(getPinPopupController().isPinCorrect())
            {
                getPinPopupController().abort();
                accept();
                return;
            }
        }
        setPinPopupController((PinPopupController) PopupCreator.showPopup("Inserisci il PIN", "/bankapp/progetto20242025piragine/fxml/popup/pinPopup.fxml", 315, 190));
    }

    private void accept()
    {
        if (BankAccountDAO.transferMoneyRequest(BankAccountDAO.getAccountById(request.getBeneficiary()), CurrentSession.getLoggedAccount(), request))
        {
            //if this popup is generated from a notification, mark it as read and close the popup
            if (n != null)
            {
                NotifyDAO.markAsRead(n.getIdNotify());
                CurrentSession.getTopbarController().updateNotifications();
                ((Stage) moneyLabel.getScene().getWindow()).close();
                return;
            }

            //if this popup is generated from a friend message, show the chat and close the popup
            BankAccountDAO.transferMoneyRequest(BankAccountDAO.getAccountById(request.getBeneficiary()), CurrentSession.getLoggedAccount(), request);
            CurrentSession.getFriendsPageController().currentFriendController.showChat();
            ((Stage) moneyLabel.getScene().getWindow()).close();
        }


    }


}