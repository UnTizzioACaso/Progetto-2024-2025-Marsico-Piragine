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
    TextField CapTextField1;

    @FXML
    TextField stateRegisterTextField;

    @FXML
    TextField provinceRegisterTextField;

    @FXML
    TextField houseNumberDayRegisterTextField;

    @FXML
    TextField cityRegisterTextField1;

    @FXML
    public void loadRegisterPage2() //loading the register page n.2
    {
        if (streetRegisterTextField.getText().isEmpty()  || CapTextField1.getText().isEmpty() || stateRegisterTextField.getText().isEmpty() || provinceRegisterTextField.getText().isEmpty()|| houseNumberDayRegisterTextField.getText().isEmpty()|| cityRegisterTextField1.getText() == null) { errorMessageLabel.setText("Tutti i campi devono essere compilati!"); }
        else
        {
            user.setAddress(streetRegisterTextField.getText());
            user.setCap(CapTextField1.getText());
            user.setState(stateRegisterTextField.getText());
            user.setProvince(provinceRegisterTextField.getText());
            user.setCity(cityRegisterTextField1.getText());
            user.setStreetNumber(houseNumberDayRegisterTextField.getText());
            rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register3.fxml", user); //loading next registerer page, is triggered by the enterRegisterButton's on action event
        }

    }
}

