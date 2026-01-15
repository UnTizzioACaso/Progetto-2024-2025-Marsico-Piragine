package bankapp.progetto20242025piragine.controller;


import bankapp.progetto20242025piragine.controller.component.TopbarController;
import bankapp.progetto20242025piragine.controller.page.BankAccountSettingsPageController;
import bankapp.progetto20242025piragine.controller.page.CardPageController;
import bankapp.progetto20242025piragine.controller.page.FriendsPageController;
import bankapp.progetto20242025piragine.controller.page.HomePageController;
import bankapp.progetto20242025piragine.db.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public  class RootWindowController extends BranchController {
    @FXML
    public BorderPane rootWindow;

    public TopbarController topbarController = null;

    private String currentPage = "";

    public User user = new User();

    public GridPane homePageGridPane;

    public HomePageController homePageController;

    public FriendsPageController friendsPageController;

    public CardPageController cardPageController;

    public BankAccountSettingsPageController bankAccountSettingsPageController;

    @FXML
    public void switchPage(String fxml) //this method sets to the center the application's main pages "rootWindow"
    {   if (!(fxml.equals(currentPage)))
        {
            try {
                currentPage = fxml;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml)); //getting the fxml in the loader
                Parent node = fxmlLoader.load(); //creating the node from the loader
                BranchController controller = fxmlLoader.getController(); //getting the controller from the loader
                controller.setRootController(this); //giving to the new page's controller the current RootController instance
                rootWindow.setCenter(node); //setting the page to the center
                controller.initializer();
            }
            catch (IOException e)
            {
                System.err.println("error loading " + fxml + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public BranchController showPopup(String title, String fxml, int width, int height)
    {
        BranchController controller =  null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml)); //getting the fxml in the loader
            Parent root = loader.load(); //creating the node from the loader
            controller = loader.getController(); //getting the controller from the loader
            controller.setRootController(this);
            Stage popupStage = new Stage(); //creating a new stage for the accountPopup
            popupStage.setTitle(title); //setting the title
            popupStage.setMinWidth(width); //setting popup's minimum width
            popupStage.setMaxWidth(width);
            popupStage.setMinHeight(height); //setting popup's minimum height
            popupStage.setMaxHeight(height);
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL); //blocking all application's windows except the popup
            popupStage.setScene(new Scene(root));
            controller.initializer();
            popupStage.showAndWait(); //blocks openAccountPopup event until the app gets closed
        }
        catch (IOException e)
        {
            System.err.println("error loading the account popup" + e.getMessage());
            e.printStackTrace();
        }
        return controller;
    }

    @FXML
    public void loadPage(String fxml) //this method sets to the center the application's main pages "rootWindow"
    {
        if (!(fxml.equals(currentPage)))
        {
            try {
                currentPage = fxml;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml)); //getting the fxml in the loader
                Parent node = fxmlLoader.load(); //creating the node from the loader
                if(fxml.equals("/bankapp/progetto20242025piragine/fxml/page/homePage.fxml"))
                {
                    homePageController = fxmlLoader.getController();
                    homePageGridPane = (GridPane) node;
                }
                else if (fxml.equals("/bankapp/progetto20242025piragine/fxml/page/friendsPage.fxml"))
                {
                    friendsPageController = fxmlLoader.getController();
                }
                else if (fxml.equals("/bankapp/progetto20242025piragine/fxml/page/cardPage.fxml"))
                {
                    cardPageController = fxmlLoader.getController();
                }
                else if (fxml.equals("/bankapp/progetto20242025piragine/fxml/page/bankAccountSettingsPage.fxml"))
                {
                    cardPageController = fxmlLoader.getController();
                }
                BranchController controller = fxmlLoader.getController(); //getting the controller from the loader
                controller.setRootController(this);//giving to the new page's controller the current RootController instance
                controller.initializer();
                rootWindow.setCenter(node); //setting the page to the center
                if (topbarController != null) //if topbarController's controller is already initialized
                {
                    topbarController.visitPage(fxml); //adds the loaded page to the backwardStack
                }
            }
            catch (IOException e)
            {
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
            BranchController controller = fxmlLoader.getController(); //getting the controller from the loader
            controller.setRootController(this);
            rootWindow.setLeft(node); //setting the node to the left
            controller.initializer();
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
            topbarController = fxmlLoader.getController(); //getting the controller from the loader
            topbarController.setRootController(this); //giving to the new page's controller the current RootController instance
            rootWindow.setTop(node); //setting the node to the top
            topbarController.initializer();
        }
        catch (IOException e)
        {
            System.err.println("error loading on top " + fxml + e.getMessage());
            e.printStackTrace();
        }
    }

}