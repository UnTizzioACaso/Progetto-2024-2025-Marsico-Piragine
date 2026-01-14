package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Register2Controller extends BranchController {

    private static final int STATE_MIN = 4;
    private static final int STATE_MAX = 100;

    private static final int PROVINCE_MIN = 2;
    private static final int PROVINCE_MAX = 100;

    private static final int CITY_MIN = 2;
    private static final int CITY_MAX = 100;

    private static final int STREET_MIN = 5;
    private static final int STREET_MAX = 255;

    private static final String TEXT_REGEX = "^[A-Za-zÀ-ÿ ]+$";

    @FXML
    private TextField streetRegisterTextField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private TextField CapTextField;

    @FXML
    private TextField stateRegisterTextField;

    @FXML
    private TextField provinceRegisterTextField;

    @FXML
    private TextField houseNumberRegisterTextField;

    @FXML
    private TextField cityRegisterTextField1;

    @FXML
    public void loadRegisterPage2()
    {
        String street = streetRegisterTextField.getText().trim();
        String cap = CapTextField.getText().trim();
        String state = stateRegisterTextField.getText().trim();
        String province = provinceRegisterTextField.getText().trim();
        String houseNumber = houseNumberRegisterTextField.getText().trim();
        String city = cityRegisterTextField1.getText().trim();

        if (street.isEmpty() || cap.isEmpty() || state.isEmpty() || province.isEmpty() || houseNumber.isEmpty() || city.isEmpty())
        {
            errorMessageLabel.setText("Tutti i campi devono essere compilati!");
            return;
        }

        if (!isLengthBetween(state, STATE_MIN, STATE_MAX))
        {
            errorMessageLabel.setText("Stato non valido");
            return;
        }

        if (!isLengthBetween(province, PROVINCE_MIN, PROVINCE_MAX))
        {
            errorMessageLabel.setText("Provincia non valida");
            return;
        }

        if (!isLengthBetween(city, CITY_MIN, CITY_MAX))
        {
            errorMessageLabel.setText("Comune non valido");
            return;
        }

        if (!isLengthBetween(street, STREET_MIN, STREET_MAX))
        {
            errorMessageLabel.setText("Indirizzo non valido");
            return;
        }

        if (!state.matches(TEXT_REGEX))
        {
            errorMessageLabel.setText("Stato non valido");
            return;
        }

        if (!province.matches(TEXT_REGEX))
        {
            errorMessageLabel.setText("Provincia non valida");
            return;
        }

        if (!city.matches(TEXT_REGEX))
        {
            errorMessageLabel.setText("Comune non valido");
            return;
        }

        if (!street.matches(TEXT_REGEX))
        {
            errorMessageLabel.setText("Indirizzo non valido");
            return;
        }

        if (!houseNumber.matches("^\\d+$"))
        {
            errorMessageLabel.setText("Il numero civico non è valido!");
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

    @FXML
    public void loadLogin()
    {
        rootController.user = new User();
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml");
    }

    private boolean isLengthBetween(String value, int min, int max)
    {
        return value.length() >= min && value.length() <= max;
    }
}
