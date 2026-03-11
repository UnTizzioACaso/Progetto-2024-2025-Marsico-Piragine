module bankapp.progetto20242025piragine {
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.smartcardio;
    requires java.management;

    // Database & Spring
    requires spring.core;
    requires spring.jdbc;
    requires org.xerial.sqlitejdbc;
    requires jbcrypt;

    // AI & Gemini (LangChain4j)
    requires langchain4j.google.ai.gemini; // Prova senza ".gemini" alla fine
    requires langchain4j;

    // NETWORKING & JSON (Indispensabili per il ChatBot)
    requires okhttp3;
    requires com.google.gson;
    requires java.net.http;

    // Esportazioni e Aperture per JavaFX
    opens bankapp.progetto20242025piragine to javafx.fxml;
    exports bankapp.progetto20242025piragine;

    // Controller principali
    exports bankapp.progetto20242025piragine.controller;
    opens bankapp.progetto20242025piragine.controller to javafx.fxml;

    // Componenti e Pagine
    exports bankapp.progetto20242025piragine.controller.component to javafx.fxml;
    opens bankapp.progetto20242025piragine.controller.component to javafx.fxml;

    opens bankapp.progetto20242025piragine.controller.page to javafx.fxml;
    opens bankapp.progetto20242025piragine.controller.widget to javafx.fxml;

    // Popup (Dove risiede la chat)
    exports bankapp.progetto20242025piragine.controller.popup to javafx.fxml;
    opens bankapp.progetto20242025piragine.controller.popup to javafx.fxml;

    // Risorse
    opens bankapp.progetto20242025piragine.css;
}