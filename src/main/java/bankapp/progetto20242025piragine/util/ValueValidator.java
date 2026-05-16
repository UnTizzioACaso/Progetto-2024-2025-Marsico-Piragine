package bankapp.progetto20242025piragine.util;

import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class ValueValidator {
    public static BigDecimal validateFormat(TextField valueField)
    {
        String value;
        if (valueField.getText().matches("^\\d+(,\\d{1,2})?$")) {
            value = valueField.getText().replace(",", ".");
            return new BigDecimal(value);
        } else if (valueField.getText().matches("^\\d+(\\.\\d{1,2})?$")) {
            value = valueField.getText();
            return new BigDecimal(value);
        }
       return null;
    }

}
