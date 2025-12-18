package bankapp.progetto20242025piragine.controller.component;

import bankapp.progetto20242025piragine.controller.BranchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;


public class TopbarController extends BranchController
{
    private Node Topbar;
    private Stack<String> backwardStack = new Stack<>(); //this stack saves the pages you explored only in forward
    private Stack<String> forwardStack = new Stack<>(); //this stack saves the pages you explored when went back to previous pages

    @FXML
    public void visitPage(String fxml) //this method updates the
    {
        backwardStack.push(fxml); //adding the new page to
        forwardStack.clear(); //deleting forward pages visited before
    }

    @FXML
    public void backPage() //this method loads last page from the backwardStack
    {
        if (backwardStack.size() > 1 ) //if the stack has at least 2 pages
        {
            String current = backwardStack.pop(); //taking current page away from the backwardStack and storing it
            forwardStack.push(current); //pushing the page removed from the backwardStack
            rootController.switchPage(backwardStack.peek()); //loading the new current page, the last on the backwardStack
        }
    }

    @FXML
    public void nextPage() //this method loads next pag from the forwardStack
    {
        if (forwardStack.size() > 0 ) //if the stack has at least one page
        {
            String page = forwardStack.pop(); //taking current page away from the forwardStack and storing it
            backwardStack.push(page); //pushing the page removed from the forwardStack
            rootController.switchPage(page); //loading the new current page, the last on the forwardStack (before it was removed)
        }
    }

    @FXML
    public void reloadPage() //loading the last page(the current one) in the backwardStack
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(backwardStack.peek())); //getting the fxml in the loader
            Parent node = fxmlLoader.load(); //creating the node from the loader
            BranchController controller = fxmlLoader.getController(); //getting the controller from the loader
            controller.setRootController(rootController); //giving to the new page's controller the current RootController instance
        }
        catch (IOException e)
        {
            System.err.println("error reloading " + backwardStack.peek() + e.getMessage());
            e.printStackTrace();
        }
    }
}
