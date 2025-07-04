module bankapp.progetto20242025marsicopiragine {
    requires javafx.controls;
    requires javafx.fxml;


    opens bankapp.progetto20242025marsicopiragine to javafx.fxml;
    exports bankapp.progetto20242025marsicopiragine;
    exports bankapp.progetto20242025marsicopiragine.controller;
    exports bankapp.progetto20242025marsicopiragine.controller.component to javafx.fxml;
    opens bankapp.progetto20242025marsicopiragine.controller to javafx.fxml;
    opens bankapp.progetto20242025marsicopiragine.controller.page to javafx.fxml;

}