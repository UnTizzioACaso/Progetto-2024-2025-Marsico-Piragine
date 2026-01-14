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
    public void loadRegisterPage2()
    {
        if (streetRegisterTextField.getText().isEmpty()
                || CapTextField.getText().isEmpty()
                || stateRegisterTextField.getText().isEmpty()
                || provinceRegisterTextField.getText().isEmpty()
                || houseNumberRegisterTextField.getText().isEmpty()
                || cityRegisterTextField1.getText() == null)
        {
            errorMessageLabel.setText("Tutti i campi devono essere compilati!");
        }
        else
        {
            String street = streetRegisterTextField.getText();
            String cap = CapTextField.getText();
            String state = stateRegisterTextField.getText();
            String province = provinceRegisterTextField.getText();
            String houseNumber = houseNumberRegisterTextField.getText();
            String city = cityRegisterTextField1.getText();

            String textRegex = "^[A-Za-zÀ-ÿ ]+$";

            if (!state.matches(textRegex) || !province.matches(textRegex) || !street.matches(textRegex))
            {
                errorMessageLabel.setText("Stato, provincia e indirizzo non possono contenere numeri o caratteri speciali!");
                return;
            }

            if (!city.matches(textRegex))
            {
                errorMessageLabel.setText("La città non può contenere numeri o caratteri speciali!");
                return;
            }

            if (!houseNumber.matches("^\\d+$"))
            {
                errorMessageLabel.setText("Il numero civico deve essere composto solo da numeri!");
                return;
            }

            int houseNumberValue;
            try
            {
                houseNumberValue = Integer.parseInt(houseNumber);
            }
            catch (NumberFormatException e)
            {
                errorMessageLabel.setText("Il numero civico non è valido!");
                return;
            }

            if (houseNumberValue < 1 || houseNumberValue > 15000)
            {
                errorMessageLabel.setText("Il numero civico non è valido!");
                return;
            }

            if (!cap.matches("^\\d{5}$"))
            {
                errorMessageLabel.setText("Il CAP non è valido!");
                return;
            }

            int capValue;
            try
            {
                capValue = Integer.parseInt(cap);
            }
            catch (NumberFormatException e)
            {
                errorMessageLabel.setText("Il CAP non è valido!");
                return;
            }

            if (capValue < 10 || capValue > 98168)
            {
                errorMessageLabel.setText("Il CAP non è valido!");
                return;
            }

            rootController.user.setAddress(street);
            rootController.user.setCap(cap);
            rootController.user.setState(state);
            rootController.user.setProvince(province);
            rootController.user.setCity(city);
            rootController.user.setStreetNumber(houseNumber);

            rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register3.fxml");
        }
    }

    @FXML
    public void loadLogin()
    {
        rootController.user = new User();
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml");
    }
}
