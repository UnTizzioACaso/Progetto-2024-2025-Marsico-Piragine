package bankapp.progetto20242025piragine.controller.page;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


public class Register2Controller extends RegisterController {

    @FXML
    TextField surnameRegisterTextField;

    @FXML
    Label errorMessageLabel;

    @FXML
    TextField nameRegisterTextField;

    @FXML
    TextField birthPlaceRegisterTextField;

    @FXML
    DatePicker birthDateRegisterDatePicker;

    @FXML
    public void loadRegisterPage2() //loading the register page n.2
    {
        if (surnameRegisterTextField.getText().isEmpty()  || nameRegisterTextField.getText().isEmpty() || birthPlaceRegisterTextField.getText().isEmpty() || birthDateRegisterDatePicker.getValue() == null) { errorMessageLabel.setText("Tutti i campi devono essere compilati!"); }
        else
        {
            user.setFirstName(nameRegisterTextField.getText());
            user.setLastName(surnameRegisterTextField.getText());
            user.setBirthPlace(birthPlaceRegisterTextField.getText());
            user.setBirthDate(birthDateRegisterDatePicker.getValue().toString());
            rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register2.fxml", user); //loading next registerer page, is triggered by the enterRegisterButton's on action event
        }

    }
}

