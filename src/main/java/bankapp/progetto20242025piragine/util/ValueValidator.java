package bankapp.progetto20242025piragine.util;

import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class ValueValidator {
    public static BigDecimal validateFormat(TextField valueField)
    {
        String text = valueField.getText().trim();

        if (text.matches("^\\d+(,\\d{1,2})?$")) {
            text = text.replace(",", ".");
            return new BigDecimal(text);
        } else if (text.matches("^\\d+(\\.\\d{1,2})?$")) {
            return new BigDecimal(text);
        }
       return null;
    }

}
