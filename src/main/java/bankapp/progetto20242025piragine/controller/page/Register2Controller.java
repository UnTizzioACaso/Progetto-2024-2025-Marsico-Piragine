package bankapp.progetto20242025piragine.controller.page;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


public class Register2Controller extends RegisterController {

    @FXML
    TextField streetRegisterTextField;

    @FXML
    Label errorMessageLabel;

    @FXML
    TextField phoneNumberRegisterTextField1;

    @FXML
    TextField stateRegisterTextField;

    @FXML
    TextField provinceRegisterTextField;



    @FXML
    public void loadRegisterPage2() //loading the register page n.2
    {
        if (streetRegisterTextField.getText().isEmpty()  || phoneNumberRegisterTextField1.getText().isEmpty() || stateRegisterTextField.getText().isEmpty() || provinceRegisterTextField.getText()== null) { errorMessageLabel.setText("Tutti i campi devono essere compilati!"); }
        else
        {
            user.setFirstName(streetRegisterTextField.getText());
            user.setLastName(phoneNumberRegisterTextField1.getText());
            user.setBirthPlace(stateRegisterTextField.getText());
            user.setBirthDate(provinceRegisterTextField.getText());
            rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register2.fxml", user); //loading next registerer page, is triggered by the enterRegisterButton's on action event
        }

    }
}

