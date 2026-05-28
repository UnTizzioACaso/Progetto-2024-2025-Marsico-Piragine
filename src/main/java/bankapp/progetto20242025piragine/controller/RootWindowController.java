package bankapp.progetto20242025piragine.controller;


import bankapp.progetto20242025piragine.controller.component.SidebarController;
import bankapp.progetto20242025piragine.controller.component.TopbarController;
import bankapp.progetto20242025piragine.controller.page.BankAccountSettingsPageController;
import bankapp.progetto20242025piragine.util.CurrentSession;
import bankapp.progetto20242025piragine.util.EasyFxmlLoader;
import bankapp.progetto20242025piragine.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Pair;

public  class RootWindowController extends BranchController {
    @FXML
    public BorderPane rootWindow;

    @FXML
    public AnchorPane centerAnchorPane;

    private String currentPage = "";

    public BankAccountSettingsPageController bankAccountSettingsPageController;



    public void switchPage(String fxml) //this method sets to the center the application's main pages "rootWindow"
    {
        if (!(fxml.equals(currentPage)))
        {
            currentPage = fxml;
            Node node = EasyFxmlLoader.loader(fxml).getValue(); //creating the node from the loader
            if(CurrentSession.getTopbarController().sliderIsActive){CurrentSession.getTopbarController().showSlider();}
            setCenter(node);
        }
    }


    public void loadPage(String fxml) //this method sets to the center the application's main pages "rootWindow"
    {
        if (!(fxml.equals(currentPage)))
        {
            currentPage = fxml;
            Pair<BranchController, Node> p = EasyFxmlLoader.loader(fxml);
            Node node = p.getValue(); //creating the node from the pair
            if (rootWindow.getTop() != null) //if topbar is already initialized
            {
                if(CurrentSession.getTopbarController().sliderIsActive){CurrentSession.getTopbarController().showSlider();}
                CurrentSession.getTopbarController().visitPage(fxml); //adds the loaded page to the backwardStack
            }
            setCenter(node);
        }
    }

    @FXML
    public void initialize() //initializing the first page to load
    {
        ThemeManager.applyTheme(rootWindow.getScene(), "light");
        currentPage = "";
        rootWindow.setLeft(null); //setting left to null because the first page is login, and it doesn't need the sidebar
        rootWindow.setTop(null); //setting top to null because the first page is login, and it doesn't need the topbar
        loadPage("/bankapp/progetto20242025piragine/fxml/page/login.fxml"); //loading login.fxml
    }


    public void loadSideBar() //this method loads a node on the left side of root's BorderPane
    {
        Pair<BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/sidebar.fxml");
        Node node = p.getValue(); //creating the node from the loader
        CurrentSession.setSidebarController((SidebarController) p.getKey()); //getting the controller from the loader
        rootWindow.setLeft(node); //setting the node to the left
    }

    public void loadTopBar() //this method loads a node on the top side of root's BorderPane and gives back his controller
    {
        Pair<BranchController, Node> p = EasyFxmlLoader.loader("/bankapp/progetto20242025piragine/fxml/component/topbar.fxml");
        Node node = p.getValue(); //creating the node from the loader
        TopbarController controller = (TopbarController) p.getKey(); //getting the controller from the loader
        rootWindow.setTop(node); //setting the node to the left
        CurrentSession.setTopbarController(controller);
    }

    public void setCenter(Node node)
    {
        centerAnchorPane.getChildren().clear(); //clears the centerAnchorPane
        centerAnchorPane.getChildren().add(node); //adds the node to the centerAnchorPane
        double d = 0;
        AnchorPane.setBottomAnchor(node, d);
        AnchorPane.setLeftAnchor(node, d);
        AnchorPane.setTopAnchor(node, d);
        AnchorPane.setRightAnchor(node, d);
    }

}