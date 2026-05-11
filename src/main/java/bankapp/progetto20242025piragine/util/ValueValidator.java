package bankapp.progetto20242025piragine.util;

import bankapp.progetto20242025piragine.db.BankAccountDAO;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.math.BigDecimal;

public class ValueValidator {
    public static String validateFormat(TextField valueField)
    {
        String value;
        if (valueField.getText().matches("^\\d+(,\\d{1,2})?$")) {
            value = valueField.getText().replace(",", ".");
            return value;
        } else if (valueField.getText().matches("^\\d+(\\.\\d{1,2})?$")) {
            value = valueField.getText();
            return value;
        }
       return null;
    }

}
