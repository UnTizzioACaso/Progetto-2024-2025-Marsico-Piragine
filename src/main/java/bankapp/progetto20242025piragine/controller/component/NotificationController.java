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
    private Label titleLabel, valueLabel, secondaryLabel;

    public Notify notify;

    @FXML
    public void readNotify()
    {

        try
        {
            if(titleLabel.getText().equals("Richiesta di denaro"))
            {
                PaymentRequestController controller = (PaymentRequestController) rootController.showPopup("Richiesta di denaro", "/bankapp/progetto20242025piragine/fxml/popup/paymentRequest.fxml", 300, 200);
                controller.idRequest = notify.getIdTransaction();
            }
            else if(valueLabel.getText().equals("Richiesta d'amicizia"))
            {
                FriendshipNotifyController controller = (FriendshipNotifyController) rootController.showPopup("Richiesta d'amicizia", "/bankapp/progetto20242025piragine/fxml/popup/frienshipNotify.fxml", 300, 200);
                controller.idRequest = notify.getIdTransaction();
            }
            NotifyDAO.markAsRead(notify.getIdNotify());
        }
        catch (SQLException e)
        {
            System.err.println("error marking notify as read " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void setCorrectValues(Notify n)
    {
        notify = n;
        if(notify.getIdTransaction() != null)
        {
            transactionNotify(notify);
        }
        else if(notify.getIdFriendRequest() != null)
        {
            friendshipRequestNotify(notify);
        }
    }


    private void friendshipRequestNotify(Notify n) //if notify is a friendship request
    {
        titleLabel.setText(getBeneficiaryUsernameF(notify.getIdFriendRequest()));
        switch (checkFriendshipStatus(notify.getIdFriendRequest()))
        {
            case "accepted":
                valueLabel.setText("Richiesta accettata");
                break;
            case "declined":
                valueLabel.setText("Richiesta rifiutata");
                break;
            case "pending":
                valueLabel.setText("Richiesta in attesa");
        }
        secondaryLabel.setText("Richiesta d'amicizia");
    }


    private String checkFriendshipStatus(int idFriendRequest) //Checks the friendship notify status
    {
        try
        {
            return  FriendRequestDAO.getFriendRequestById(idFriendRequest).getStatus();
        }
        catch(SQLException e)
        {
            System.err.println("error getting friend request by id " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    private String getBeneficiaryUsernameF(int idFriendRequest) //gets the username of friendship's beneficiary
    {
        try
        {
            FriendRequest r = FriendRequestDAO.getFriendRequestById(idFriendRequest);
            try
            {
                return UserDAO.getUsernameByUserId(r.getBeneficiary());

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



    private void transactionNotify(Notify n)
    {
        titleLabel.setText(getBeneficiaryUsernameT(notify.getIdTransaction()));
        valueLabel.setText(getTransactionRequestValue(notify.getIdTransaction()).toString());
        secondaryLabel.setText("Richiesta di denaro");
    }
}
