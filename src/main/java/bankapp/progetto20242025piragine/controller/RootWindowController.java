package bankapp.progetto20242025piragine.controller;


import bankapp.progetto20242025piragine.controller.component.TopbarController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public  class RootWindowController extends BranchController {
    @FXML
    private BorderPane rootWindow;

    private TopbarController topbar = null;

    private String currentPage = "";

    @FXML
    public void loadPage(String fxml) //this method sets to the center the application's main pages "rootWindow"
    {
        if (!(fxml.equals(currentPage)))
        {
            try {
                currentPage = fxml;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml)); //getting the fxml in the loader
                Parent node = fxmlLoader.load(); //creating the node from the loader
                BranchController controller = fxmlLoader.getController(); //getting the controller from the loader
                controller.setRootController(this); //giving to the new page's controller the current RootController instance
                rootWindow.setCenter(node); //setting the page to the center
                if (topbar != null) //if topbar's controller is already initialized
                {
                    topbar.visitPage(fxml); //adds the loaded page to the backwardStack
                }
            } catch (IOException e) {
                System.err.println("error loading " + fxml + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() //initializing the first page to load
    {
        loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml"); //loading login.fxml
    }


    public void loadSideBar(String fxml) //this method loads a node on the left side of root's BorderPane
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml)); //getting the fxml in the loader
            Parent node = fxmlLoader.load(); //creating the node from the loader
            rootWindow.setLeft(node); //setting the node to the left
        }
        catch (IOException e)
        {
            System.err.println("error loading on the left " + fxml + e.getMessage());
            e.printStackTrace();
        }
    }
    public void loadTopBar(String fxml) //this method loads a node on the top side of root's BorderPane and gives back his controller
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml)); //getting the fxml in the loader
            Parent node = fxmlLoader.load(); //creating the node from the loader
            topbar = fxmlLoader.getController(); //getting the controller from the loader
            topbar.setRootController(this); //giving to the new page's controller the current RootController instance
            rootWindow.setTop(node); //setting the node to the top
        }
        catch (IOException e)
        {
            System.err.println("error loading on top " + fxml + e.getMessage());
            e.printStackTrace();
        }
    }
}