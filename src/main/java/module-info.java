module bankapp.progetto20242025piragine {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.core;
    requires spring.jdbc;
    requires org.xerial.sqlitejdbc;


    opens bankapp.progetto20242025piragine to javafx.fxml;
    exports bankapp.progetto20242025piragine;
    exports bankapp.progetto20242025piragine.controller;
    exports bankapp.progetto20242025piragine.controller.component to javafx.fxml;
    opens bankapp.progetto20242025piragine.controller to javafx.fxml;
    opens bankapp.progetto20242025piragine.controller.page to javafx.fxml;

}