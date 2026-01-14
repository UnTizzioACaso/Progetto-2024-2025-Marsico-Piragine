package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.User;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.Period;

public class Register1Controller extends BranchController {

    private static final int NAME_MIN_LENGTH = 2;
    private static final int NAME_MAX_LENGTH = 50;
    private static final int BIRTH_PLACE_MIN_LENGTH = 3;
    private static final int BIRTH_PLACE_MAX_LENGTH = 255;
    private static final String NAME_REGEX = "^[A-Za-zÀ-ÿ ]+$";

    @FXML
    private TextField nameRegisterTextField;

    @FXML
    private TextField surnameRegisterTextField;

    @FXML
    private TextField birthPlaceRegisterTextField;

    @FXML
    private DatePicker birthDateRegisterDatePicker;

    @FXML
    private ChoiceBox<String> sexChoiceBox;

    @FXML
    private Label errorMessageLabel;

    @FXML
    public void loadRegisterPage2()
    {
        String name = nameRegisterTextField.getText().trim();
        String surname = surnameRegisterTextField.getText().trim();
        String birthPlace = birthPlaceRegisterTextField.getText().trim();
        LocalDate birthDate = birthDateRegisterDatePicker.getValue();
        String gender = sexChoiceBox.getValue();

        if (name.isEmpty() || surname.isEmpty() || birthPlace.isEmpty() || birthDate == null || gender == null)
        {
            errorMessageLabel.setText("Tutti i campi devono essere compilati!");
            return;
        }

        if (gender.equals("- Sesso -"))
        {
            errorMessageLabel.setText("Devi selezionare un genere valido!");
            return;
        }

        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 18)
        {
            errorMessageLabel.setText("Devi avere almeno 18 anni per registrarti!");
            return;
        }

        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH ||
                surname.length() < NAME_MIN_LENGTH || surname.length() > NAME_MAX_LENGTH)
        {
            errorMessageLabel.setText(
                    "Nome e cognome devono contenere tra "
                            + NAME_MIN_LENGTH + " e " + NAME_MAX_LENGTH + " caratteri!"
            );
            return;
        }

        if (birthPlace.length() < BIRTH_PLACE_MIN_LENGTH || birthPlace.length() > BIRTH_PLACE_MAX_LENGTH)
        {
            errorMessageLabel.setText("Luogo di nascita non valido");
            return;
        }

        if (!name.matches(NAME_REGEX) || !surname.matches(NAME_REGEX) || !birthPlace.matches(NAME_REGEX))
        {
            errorMessageLabel.setText(
                    "Nome, cognome e luogo di nascita non possono contenere numeri o caratteri speciali!"
            );
            return;
        }

        rootController.user.setFirstName(name);
        rootController.user.setLastName(surname);
        rootController.user.setBirthPlace(birthPlace);
        rootController.user.setBirthDate(birthDate.toString());
        rootController.user.setGender(gender);

        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register2.fxml");
    }

    @FXML
    public void loadLogin()
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml");
    }

    @Override
    public void initializer()
    {
        rootController.user = new User();
        sexChoiceBox.setValue("- Sesso -");
    }
}
