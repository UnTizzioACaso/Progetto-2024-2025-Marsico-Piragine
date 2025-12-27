package bankapp.progetto20242025piragine.controller.page;


import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.User;
import bankapp.progetto20242025piragine.db.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;

public class LoginController extends BranchController {

    @FXML
    private TextField emailLoginTextField;

    @FXML
    private TextField passwordLoginTextField;

    @FXML
    private Label accessErrorMessageLabel;

    @FXML
    public void loadRegisterPage() //switching to the register section
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register1.fxml"); //loading first register page
    }

    @FXML
    public void loadHomePage() throws SQLException //giving access to the homepage
    {
        //if (passwordLoginTextField.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$") && emailLoginTextField.getText().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) //checking if the password and the email are valid
        //{
            if(UserDAO.loginCheck(emailLoginTextField.getText(), passwordLoginTextField.getText()))
            {
                rootController.user = UserDAO.getUserByEmail(emailLoginTextField.getText());
                rootController.loadSideBar("/bankapp/progetto20242025piragine/fxml/component/sidebar.fxml"); //loading the sidebar
                rootController.loadTopBar("/bankapp/progetto20242025piragine/fxml/component/topbar.fxml"); //loading the topbar
                rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml"); //loading the home page
            }
       // }
        //else
        {
            accessErrorMessageLabel.setText("credenziali errate riprova"); //giving the message error if the password or email is not valid
        }
    }

    @FXML
    public void showPassword()
    {
    }



}