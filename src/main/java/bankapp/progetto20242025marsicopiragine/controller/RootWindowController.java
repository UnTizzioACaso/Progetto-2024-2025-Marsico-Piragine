package bankapp.progetto20242025marsicopiragine.controller;

import bankapp.progetto20242025marsicopiragine.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class RootWindowController {

    public BorderPane rootWindow;
    @FXML
    public void initialize()
    {
        try {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("fxml/page/login.fxml"));
        Node n = loader.load();
        rootWindow.setCenter(n);
        } catch (IOException e) {
           System.err.println("error during window initialization" + e.getMessage());
         e.printStackTrace();
        }

    }
}