package bankapp.progetto20242025piragine.util;

import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class ValueValidator {


    public static BigDecimal validateFormat(String text) //text version
    {
        if (text == null) {return null;}

        text = text.trim();

        //If starts with a decimal and is optionally followed by a comma and max 2 integers
        if (text.matches("^\\d+(,\\d{1,2})?$"))
        {
            text = text.replace(",", ".");
            return new BigDecimal(text);
        }

        //If starts with a decimal and is optionally followed by a dot and max 2 integers
        else if (text.matches("^\\d+(\\.\\d{1,2})?$")) {return new BigDecimal(text);}

        return null;
    }

}
