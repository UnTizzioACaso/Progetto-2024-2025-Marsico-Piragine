package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.FriendshipNotifyController;
import bankapp.progetto20242025piragine.controller.popup.PaymentRequestController;
import bankapp.progetto20242025piragine.dao.*;
import bankapp.progetto20242025piragine.model.FriendRequest;
import bankapp.progetto20242025piragine.model.Notify;
import bankapp.progetto20242025piragine.model.Transaction;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class NotificationController extends BranchController{

    @FXML
    private Label notifyTitleLabel, valueLabel, secondaryLabel;

    public Notify notify;

    public String type;

    private Boolean imTheSender = false;

    @FXML
    public void readNotify()
    {
        if(imTheSender && secondaryLabel.getText().equals("Richiesta di denaro")) //if notify is a transaction request from user
        {
            Transaction t = TransactionDAO.getTransactionById(notify.getIdTransaction());
            if(t.getStatus().equals("accepted") || t.getStatus().equals("declined")) //if it is not pending
            {
                NotifyDAO.markAsRead(notify.getIdNotify());
                CurrentSession.getTopbarController().updateNotifications();
            }
        }

        else if(secondaryLabel.getText().equals("Richiesta di denaro"))
        {
            PaymentRequestController controller = (PaymentRequestController) PopupCreator.showPopup("Richiesta di denaro", "/bankapp/progetto20242025piragine/fxml/popup/paymentRequest.fxml", 300, 200);
            controller.n = notify;
        }

        else if(secondaryLabel.getText().equals("Richiesta d'amicizia") && imTheSender) //if notify is a friendship request from user
        {
            FriendRequest f = FriendRequestDAO.getFriendRequestById(notify.getIdFriendRequest());
            if(f.getStatus().equals("accepted") || f.getStatus().equals("declined")) //if it is not pending
            {
                NotifyDAO.markAsRead(notify.getIdNotify());
                CurrentSession.getTopbarController().updateNotifications();
            }
        }

        else if(secondaryLabel.getText().equals("Richiesta d'amicizia"))
        {
            FriendshipNotifyController controller = (FriendshipNotifyController) PopupCreator.showPopup("Richiesta d'amicizia", "/bankapp/progetto20242025piragine/fxml/popup/frienshipNotify.fxml", 300, 200);
            controller.idRequest = notify.getIdFriendRequest();
            controller.friendshipUsernameLabel.setText(notifyTitleLabel.getText());
        }
    }


    public void setCorrectValues(Notify n)
    {
        notify = n;
        if(notify.getIdTransaction() != null)
        {
            transactionRequestNotify();
        }
        else if(notify.getIdFriendRequest() != null)
        {
            friendshipRequestNotify();
        }
    }

    private void transactionRequestNotify()
    {
        Transaction t = TransactionDAO.getTransactionById(notify.getIdTransaction());
        String status;
        switch (t.getStatus())
        {
            case "accepted":
                status = "(accettata)";

                break;
            case "declined":
                status = "(rifiutata)";
                break;
            case "pending":
                status = "(in attesa)";
                break;
            default:
                status = "";
        }

        String note = "";
        if (t.getNote() != null || t.getNote() != "")
        {
            note =  t.getNote();
        }
        if(t.getType().equals("request") && t.getBeneficiary().equals(CurrentSession.getLoggedAccount().getIdAccount())) //if transaction is a request and the user is the beneficiary
        {
            imTheSender = true;
            notifyTitleLabel.setText(getSenderUsernameT(t.getIdTransaction()));;
            valueLabel.setText( t.getAmount().toString() + "€ " + status);
            secondaryLabel.setText("Richiesta di denaro: " + note);
        }
        else if (t.getType().equals("request") && t.getSender().equals(CurrentSession.getLoggedAccount().getIdAccount())) //if transaction is a request and the user is the sender
        {
            imTheSender = false;
            notifyTitleLabel.setText(getBeneficiaryUsernameT(t.getIdTransaction()));
            valueLabel.setText(t.getAmount().toString() + "€");
            secondaryLabel.setText("Richiesta di denaro: " + note);
        }
        else if (t.getType().equals("payment")) //if transaction is a payment
        {
            imTheSender = false;
            notifyTitleLabel.setText(getBeneficiaryUsernameT(t.getIdTransaction()));
            valueLabel.setText("-" + t.getAmount().toString() + "€");
            secondaryLabel.setText("Pagamento " + note);
        }
        else if(t.getType().equals("donation") && t.getSender().equals(CurrentSession.getLoggedAccount().getIdAccount())) //if transaction is a donation from the user
        {
            imTheSender = true;
            notifyTitleLabel.setText(getBeneficiaryUsernameT(t.getIdTransaction()));
            valueLabel.setText("-" + t.getAmount().toString() + "€");
            secondaryLabel.setText("Donazione " + note);
        }
        else if (t.getType().equals("donation") && t.getBeneficiary().equals(CurrentSession.getLoggedAccount().getIdAccount())) //if transaction is a donation to the user
        {
            imTheSender = false;
            notifyTitleLabel.setText(getSenderUsernameT(t.getIdTransaction()));;
            valueLabel.setText(t.getAmount().toString() + "€");
            secondaryLabel.setText("Donazione " + note);
        }
    }

    private void friendshipRequestNotify() //if notify is a friendship request
    {
        int id = FriendRequestDAO.getFriendRequestById(notify.getIdFriendRequest()).getRequested();
        if( id == CurrentSession.getLoggedUser().getUserID())//user is the beneficiary
        {
            valueLabel.setText("");
            secondaryLabel.setText("Richiesta d'amicizia");
            notifyTitleLabel.setText(getSenderUsernameF(notify.getIdFriendRequest()));
            return;
        }

        imTheSender = true;
        try
        {
            notifyTitleLabel.setText(UserDAO.getUsernameByUserId(id));
        } catch (SQLException e) {
            System.err.println("error getting the receiver" + e.getMessage());
            e.printStackTrace();
        }
        switch (FriendRequestDAO.getFriendRequestById(notify.getIdFriendRequest()).getStatus())
        {
            case "accepted":
                valueLabel.setText("accettata");
                break;
            case "declined":
                valueLabel.setText("rifiutata");
                break;
            case "pending":
                valueLabel.setText("in attesa");
        }
        secondaryLabel.setText("Richiesta d'amicizia");
    }




    private String getBeneficiaryUsernameT(int idTransaction)
    {

            Transaction t = TransactionDAO.getTransactionById(idTransaction);
            try
            {
                return UserDAO.getUsernameByUserId(t.getBeneficiary());

            }
            catch(SQLException e)
            {
                System.err.println("error getting username by user id " + e);
                e.printStackTrace();
                return null;
            }
    }

    private String getSenderUsernameF(int idFriendRequest) {

            FriendRequest f = FriendRequestDAO.getFriendRequestById(idFriendRequest);

            try
            {
                return UserDAO.getUsernameByUserId(f.getRequester());
            }
            catch (SQLException e) 
            {
                System.err.println("error getting username by user id " + e);
                e.printStackTrace();
                return null;
            }

    }

    private String getSenderUsernameT(int idTransaction) {

        Transaction t = TransactionDAO.getTransactionById(idTransaction);

        try
        {
            return UserDAO.getUsernameByUserId(t.getSender());
        }
        catch (SQLException e)
        {
            System.err.println("error getting username by user id " + e);
            e.printStackTrace();
            return null;
        }

    }
}
