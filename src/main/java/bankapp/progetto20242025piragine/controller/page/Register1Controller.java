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
        if (surnameRegisterTextField.getText().isEmpty()  || nameRegisterTextField.getText().isEmpty() || birthPlaceRegisterTextField.getText().isEmpty() || birthDateRegisterDatePicker.getValue()==null|| sexChoiceBox.getValue() == null ) //checking if all forms are compiled
        {
            errorMessageLabel.setText("Tutti i campi devono essere compilati!"); //giving the message error
        }

        else
        {
            LocalDate birthDate = birthDateRegisterDatePicker.getValue(); //getting the birthdate from the date picker
            LocalDate today = LocalDate.now(); //getting today's date
            int age = Period.between(birthDate, today).getYears(); //calculating the period between today's date with the birthdate, in years

            if (sexChoiceBox.getValue().equals("- Sesso -")) //checking if a valid gender is selected
            {
                errorMessageLabel.setText("Devi selezionare un genere valido!"); //giving the message error
                return; //stopping the code
            }

            if (age < 18) //if the period is under 18 years
            {
                errorMessageLabel.setText("Devi avere almeno 18 anni per registrarti!"); //giving the message error
                return; //stopping the code
            }

            rootController.user.setFirstName(nameRegisterTextField.getText()); //getting the name from the respective form for the user obj
            rootController.user.setLastName(surnameRegisterTextField.getText()); //getting the surname from the respective form for the user obj
            rootController.user.setBirthPlace(birthPlaceRegisterTextField.getText()); //getting the birthplace from the respective form for the user obj
            rootController.user.setBirthDate(birthDateRegisterDatePicker.getValue().toString()); //getting the birthdate from the respective form for the user obj
            rootController.user.setGender(sexChoiceBox.getValue()); //getting the gender from the respective form for the user obj
            rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/register2.fxml"); //loading next registerer page, is triggered by the enterRegisterButton's on action event
        }
    }

    @FXML public void loadLogin()
    {
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml");
    }

    @Override
    public void initializer() //initializing the page
    {
        rootController.user = new User();
        sexChoiceBox.setValue("- Sesso -"); //giving to sexChoiceBox the standard value "- Sesso -"
    }
}

