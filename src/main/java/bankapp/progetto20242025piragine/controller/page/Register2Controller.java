package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Register2Controller extends BranchController {

    // TextField for street name
    @FXML
    TextField streetRegisterTextField;

    // Label used to display validation error messages
    @FXML
    Label errorMessageLabel;

    // TextField for postal code (CAP)
    @FXML
    TextField CapTextField;

    // TextField for state
    @FXML
    TextField stateRegisterTextField;

    // TextField for province
    @FXML
    TextField provinceRegisterTextField;

    // TextField for house/street number
    @FXML
    TextField houseNumberRegisterTextField;

    // TextField for city
    @FXML
    TextField cityRegisterTextField1;

    // Validates address data and loads the third registration page
    @FXML
    public void loadRegisterPage2() // loading the second registration page
    {
        // Check if all required address fields are filled
        if (streetRegisterTextField.getText().isEmpty() || CapTextField.getText().isEmpty() || stateRegisterTextField.getText().isEmpty() || provinceRegisterTextField.getText().isEmpty() || houseNumberRegisterTextField.getText().isEmpty() || cityRegisterTextField1.getText() == null)
        {
            errorMessageLabel.setText("Tutti i campi devono essere compilati!"); // error message if any field is empty
        }
        else
        {
            // Save address information into the shared User object
            rootController.user.setAddress(streetRegisterTextField.getText()); // set user's street address
            rootController.user.setCap(CapTextField.getText()); // set user's postal code (CAP)
            rootController.user.setState(stateRegisterTextField.getText()); // set user's state
            rootController.user.setProvince(provinceRegisterTextField.getText()); // set user's province
            rootController.user.setCity(cityRegisterTextField1.getText()); // set user's city
            rootController.user.setStreetNumber(houseNumberRegisterTextField.getText()); // set user's house number

            // Load the next registration page
            rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register3.fxml");
        }
    }

    // Returns to the login page and resets the User object
    @FXML
    public void loadLogin()
    {
        rootController.user = new User(); // reset user data
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml"); // load login page
    }
}
