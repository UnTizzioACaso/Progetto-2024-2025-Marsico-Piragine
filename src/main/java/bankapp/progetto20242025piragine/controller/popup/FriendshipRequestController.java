package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.sql.SQLException;

public class FriendshipRequestController extends BranchController {
    @FXML
    private TextField searchByUsernameField;

    @FXML
    private TextField searchByPhoneNumberField;

    @FXML
    private TextField searchByEmailField;

    @FXML
    private ToggleButton toggleUsername;

    @FXML
    private ToggleButton toggleEmail;

    @FXML
    private ToggleButton togglePhoneNumber;



    @FXML
    public void switchToUsername() {
        searchByUsernameField.setDisable(false);
        searchByUsernameField.setOpacity(1.0);
        searchByPhoneNumberField.setDisable(true);
        searchByPhoneNumberField.setOpacity(0.0);
        searchByEmailField.setDisable(true);
        searchByEmailField.setOpacity(0.0);
        toggleUsername.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 8 0 0 8; -fx-border-color: transparent;");
        toggleEmail.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 0; -fx-border-color: gray; -fx-border-width: 0 1 0 1;");
        togglePhoneNumber.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 0 8 8 0; -fx-border-color: transparent;");
    }


    @FXML
    public void switchToPhoneNumber() {
        searchByUsernameField.setDisable(true);
        searchByUsernameField.setOpacity(0.0);
        searchByPhoneNumberField.setDisable(false);
        searchByPhoneNumberField.setOpacity(1.0);
        searchByEmailField.setDisable(true);
        searchByEmailField.setOpacity(0.0);
        toggleUsername.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 0 8 8 0; -fx-border-color: transparent;");
        toggleEmail.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 0; -fx-border-color: gray; -fx-border-width: 0 1 0 1;");
        togglePhoneNumber.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 8 0 0 8; -fx-border-color: transparent;");

    }

    @FXML
    public void switchToEmail() {
        searchByUsernameField.setDisable(true);
        searchByUsernameField.setOpacity(0.0);
        searchByPhoneNumberField.setDisable(true);
        searchByPhoneNumberField.setOpacity(0.0);
        searchByEmailField.setDisable(false);
        searchByEmailField.setOpacity(1.0);
        toggleUsername.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 0 8 8 0; -fx-border-color: transparent;");
        toggleEmail.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 0; -fx-border-color: gray; -fx-border-width: 0 1 0 1;");
        togglePhoneNumber.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-background-radius: 0 8 8 0; -fx-border-color: transparent;");
    }

    @FXML
    public void sendRequest() {
        //Send request with username
        if (!searchByUsernameField.isDisable()) {
            User user = new User();
            try {
                user = UserDAO.getUserByUsername(searchByUsernameField.getText());
            } catch (SQLException e) {
                System.err.println("error during user research in db by username" + e.getMessage());
                e.printStackTrace();
            }

            if (user.getUsername() != null) {
                FriendRequest request = new FriendRequest(rootController.user.getUserID(), user.getUserID());
                try {
                    FriendRequestDAO.sendRequest(request);
                } catch (SQLException e) {
                    System.err.println("error during request sendRequest " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        //Send request with email
        if (!searchByEmailField.isDisable()) {
            User user = new User();
            try {
                user = UserDAO.getUserByEmail(searchByEmailField.getText());
            } catch (SQLException e) {
                System.err.println("error during user research in db by email " + e.getMessage());
                e.printStackTrace();
            }

            if (user.getUsername() != null) {
                FriendRequest request = new FriendRequest(rootController.user.getUserID(), user.getUserID());
                try {
                    FriendRequestDAO.sendRequest(request);
                } catch (SQLException e) {
                    System.err.println("error during request sendRequest " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        //Send request with phone number
        if (!searchByPhoneNumberField.isDisable()) {
            User user = new User();
            try {
                user = UserDAO.getUserByCellphone(searchByPhoneNumberField.getText());
            } catch (SQLException e) {
                System.err.println("error during user research in db by phone number " + e.getMessage());
                e.printStackTrace();
            }

            if (user.getUsername() != null) {
                FriendRequest request = new FriendRequest(rootController.user.getUserID(), user.getUserID());
                try {
                    FriendRequestDAO.sendRequest(request);
                } catch (SQLException e) {
                    System.err.println("error during request sendRequest " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}

