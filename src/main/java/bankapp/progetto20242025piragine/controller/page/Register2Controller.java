package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


public class Register2Controller extends BranchController {

    @FXML
    TextField streetRegisterTextField;

    @FXML
    Label errorMessageLabel;

    @FXML
    TextField CapTextField;

    @FXML
    TextField stateRegisterTextField;

    @FXML
    TextField provinceRegisterTextField;

    @FXML
    TextField houseNumberRegisterTextField;

    @FXML
    TextField cityRegisterTextField1;

    @FXML
    public void loadRegisterPage2() //loading the register page n.2
    {
        if (streetRegisterTextField.getText().isEmpty()  || CapTextField.getText().isEmpty() || stateRegisterTextField.getText().isEmpty() || provinceRegisterTextField.getText().isEmpty()|| houseNumberRegisterTextField.getText().isEmpty()|| cityRegisterTextField1.getText() == null) //checking if all forms are compiled
        {
            errorMessageLabel.setText("Tutti i campi devono essere compilati!");} //giving the message error

        else
        {
            rootController.user.setAddress(streetRegisterTextField.getText()); //setting the address parameter to the User obj
            rootController.user.setCap(CapTextField.getText()); //setting the cap parameter to the User obj
            rootController.user.setState(stateRegisterTextField.getText()); //setting the state parameter to the User obj
            rootController.user.setProvince(provinceRegisterTextField.getText()); //setting the province parameter to the User obj
            rootController.user.setCity(cityRegisterTextField1.getText()); //setting the province parameter to the User obj
            rootController.user.setStreetNumber(houseNumberRegisterTextField.getText()); //setting the street number parameter to the User obj
            rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register3.fxml"); //loading next registerer page, is triggered by the enterRegisterButton's on action event
        }
    }

    @FXML
    public void loadLogin()
    {
        rootController.user = new User();
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml");
    }

}

