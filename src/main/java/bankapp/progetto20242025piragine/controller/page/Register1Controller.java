package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.User;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.time.LocalDate;
import java.time.Period;

public class Register1Controller extends BranchController {

    // TextField for user's surname
    @FXML
    TextField surnameRegisterTextField;

    // Label used to display validation error messages
    @FXML
    Label errorMessageLabel;

    // TextField for user's name
    @FXML
    TextField nameRegisterTextField;

    // TextField for user's birth place
    @FXML
    TextField birthPlaceRegisterTextField;

    // DatePicker for user's birth date
    @FXML
    DatePicker birthDateRegisterDatePicker;

    // ChoiceBox for user's gender selection
    @FXML
    ChoiceBox<String> sexChoiceBox;

    // Validates input fields and loads the second registration page
    @FXML
    public void loadRegisterPage2() // loading the second registration page
    {
        // Check if all required fields are filled
        if (surnameRegisterTextField.getText().isEmpty() || nameRegisterTextField.getText().isEmpty() || birthPlaceRegisterTextField.getText().isEmpty() || birthDateRegisterDatePicker.getValue() == null || sexChoiceBox.getValue() == null)
        {
            errorMessageLabel.setText("Tutti i campi devono essere compilati!"); // error message if any field is empty
        }
        else
        {
            // Retrieve selected birth date
            LocalDate birthDate = birthDateRegisterDatePicker.getValue();

            // Get current date
            LocalDate today = LocalDate.now();

            // Calculate user's age in years
            int age = Period.between(birthDate, today).getYears();

            // Check if a valid gender has been selected
            if (sexChoiceBox.getValue().equals("- Sesso -"))
            {
                errorMessageLabel.setText("Devi selezionare un genere valido!"); // invalid gender selection message
                return; // stop execution
            }

            // Check if user is at least 18 years old
            if (age < 18)
            {
                errorMessageLabel.setText("Devi avere almeno 18 anni per registrarti!"); // underage error message
                return; // stop execution
            }

            String name = nameRegisterTextField.getText();
            String surname = surnameRegisterTextField.getText();
            String birthPlace = birthPlaceRegisterTextField.getText();

            String regex = "^[A-Za-zÀ-ÿ ]+$";

            if (!name.matches(regex) || !surname.matches(regex) || !birthPlace.matches(regex))
            {
                errorMessageLabel.setText("Nome, cognome e luogo di nascita non possono contenere numeri o caratteri speciali!");
                return;
            }

            // Save user personal data into the shared User object
            rootController.user.setFirstName(nameRegisterTextField.getText()); // set user's name
            rootController.user.setLastName(surnameRegisterTextField.getText()); // set user's surname
            rootController.user.setBirthPlace(birthPlaceRegisterTextField.getText()); // set user's birth place
            rootController.user.setBirthDate(birthDateRegisterDatePicker.getValue().toString()); // set user's birth date
            rootController.user.setGender(sexChoiceBox.getValue()); // set user's gender

            // Load the next registration page
            rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register2.fxml");
        }
    }

    // Loads the login page
    @FXML
    public void loadLogin()
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml");
    }

    // Initializes the registration page
    @Override
    public void initializer() // initializing the page
    {
        // Create a new User instance for the registration process
        rootController.user = new User();

        // Set default value for the gender ChoiceBox
        sexChoiceBox.setValue("- Sesso -");
    }
}
