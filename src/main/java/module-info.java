module bankapp.progetto20242025piragine {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.core;
    requires spring.jdbc;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;
    requires jbcrypt;
    requires java.smartcardio;


    opens bankapp.progetto20242025piragine to javafx.fxml;
    exports bankapp.progetto20242025piragine;
    exports bankapp.progetto20242025piragine.controller;
    exports bankapp.progetto20242025piragine.controller.component to javafx.fxml;
    opens bankapp.progetto20242025piragine.controller to javafx.fxml;
    opens bankapp.progetto20242025piragine.controller.page to javafx.fxml;
    opens bankapp.progetto20242025piragine.controller.component to javafx.fxml;
    opens bankapp.progetto20242025piragine.controller.widget to javafx.fxml;
    opens bankapp.progetto20242025piragine.css;
    opens bankapp.progetto20242025piragine.controller.popup to javafx.fxml;



}