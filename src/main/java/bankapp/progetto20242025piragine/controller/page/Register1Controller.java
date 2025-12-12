package bankapp.progetto20242025piragine.controller.page;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.time.LocalDate;
import java.time.Period;


public class Register1Controller extends RegisterController {

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
    ChoiceBox<String> sexChoiceBox;

    @FXML
    public void loadRegisterPage2() //loading the register page n.2
    {
        if (surnameRegisterTextField.getText().isEmpty()  || nameRegisterTextField.getText().isEmpty() || birthPlaceRegisterTextField.getText().isEmpty() || birthDateRegisterDatePicker.getValue()==null|| sexChoiceBox.getValue() == null )
        {
            errorMessageLabel.setText("Tutti i campi devono essere compilati!");
        }

        else
        {
            LocalDate birthDate = birthDateRegisterDatePicker.getValue();
            LocalDate today = LocalDate.now();
            int age = Period.between(birthDate, today).getYears();

            if (age < 18) {
                errorMessageLabel.setText("Devi avere almeno 18 anni per registrarti!");
                return;
            }

            if (sexChoiceBox.getValue().equals("- Sesso -"))
            { errorMessageLabel.setText("Devi selezionare un genere valido!");}
            else
            {
                System.out.println(sexChoiceBox.getValue().toString());
                user.setFirstName(nameRegisterTextField.getText());
                user.setLastName(surnameRegisterTextField.getText());
                user.setBirthPlace(birthPlaceRegisterTextField.getText());
                user.setBirthDate(birthDateRegisterDatePicker.getValue().toString());
                user.setGender(sexChoiceBox.getValue().toString());
                rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register2.fxml", user); //loading next registerer page, is triggered by the enterRegisterButton's on action event

            }

                    }

    }

    @FXML
    public void initialize()
    {
        sexChoiceBox.setValue("- Sesso -");
    }
}

