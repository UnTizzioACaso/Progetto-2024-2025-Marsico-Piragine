package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.popup.FriendshipNotifyController;
import bankapp.progetto20242025piragine.controller.popup.PaymentRequestController;
import bankapp.progetto20242025piragine.db.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.sql.SQLException;

public class NotificationController extends BranchController{

    @FXML
    private Label notifyTitleLabel, valueLabel, secondaryLabel;

    public Notify notify;

    private Boolean imTheSender = false;

    @FXML
    public void readNotify()
    {

        // NotifyDAO.markAsRead(notify.getIdNotify());

        if(imTheSender)
        {
            rootController.topbarController.updateNotifications();
            return;
        }

        if(secondaryLabel.getText().equals("Richiesta di denaro"))
        {
            PaymentRequestController controller = (PaymentRequestController) rootController.showPopup("Richiesta di denaro", "/bankapp/progetto20242025piragine/fxml/popup/paymentRequest.fxml", 300, 200);
            controller.idRequest = notify.getIdTransaction();
        }
        else if(secondaryLabel.getText().equals("Richiesta d'amicizia"))
        {
            FriendshipNotifyController controller = (FriendshipNotifyController) rootController.showPopup("Richiesta d'amicizia", "/bankapp/progetto20242025piragine/fxml/popup/frienshipNotify.fxml", 300, 200);
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
        notifyTitleLabel.setText(getBeneficiaryUsernameT(notify.getIdTransaction()));
        valueLabel.setText(getTransactionRequestValue(notify.getIdTransaction()).toString());
        secondaryLabel.setText("Richiesta di denaro");
    }

    private void friendshipRequestNotify() //if notify is a friendship request
    {
        int id = FriendRequestDAO.getFriendRequestById(notify.getIdFriendRequest()).getRequested();
        if( id == rootController.user.getUserID())//user is the beneficiary
        {
            valueLabel.setText("");
            secondaryLabel.setText("Richiesta d'amicizia");
            notifyTitleLabel.setText(getRequesterUsernameF(notify.getIdFriendRequest()));
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
        try
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
        catch(SQLException e)
        {
            System.err.println("error getting friend request by id " + e);
            e.printStackTrace();
            return null;
        }
    }

    private String getRequesterUsernameT(int idTransaction) {
        try {
            Transaction t = TransactionDAO.getTransactionById(idTransaction);
            try {
                return UserDAO.getUsernameByUserId(t.getSender());

            } catch (SQLException e) {
                System.err.println("error getting username by user id " + e);
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            System.err.println("error getting friend request by id " + e);
            e.printStackTrace();
            return null;
        }
    }

    private String getRequesterUsernameF(int idFriendRequest) {

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

    private BigDecimal getTransactionRequestValue(int idTransaction)
    {
        try
        {
            return TransactionDAO.getTransactionById(idTransaction).getAmount();
        }
        catch (SQLException e)
        {
            System.err.println("error getting friend request by id " + e);
            e.printStackTrace();
            return null;
        }
    }




}
