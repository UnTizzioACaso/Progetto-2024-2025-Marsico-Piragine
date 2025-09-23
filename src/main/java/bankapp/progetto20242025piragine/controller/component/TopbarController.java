package bankapp.progetto20242025piragine.controller.component;
import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.RootWindowController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import java.util.Stack;
import java.util.Vector;

public class TopbarController extends BranchController {



    private Node Topbar;

    private Stack<String> backwardStack = new Stack<>(); //this stack saves the pages you explored only in forward
    private Stack<String> forwardStack = new Stack<>(); //this stack saves the pages you explored when went back to previous pages

    @FXML
    public void visitPage(String fxml)//this method updates the
    {
        if (backwardStack.size() < 1)
        {
            backwardStack.push(fxml); //adding the new page to
            forwardStack.clear(); //deleting forward pages visited before
        }

        else if (!(backwardStack.peek().equals(fxml))); //if the last page is different from new page
        {
            backwardStack.push(fxml); //adding the new page to
            forwardStack.clear(); //deleting forward pages visited before
        }

    }

    @FXML
    public void backPage() //this method loads last page from the backwardStack
    {
        if (backwardStack.size() > 1) //if the stack has at least 2 pages
        {
            String current = backwardStack.pop(); //taking current page away from the backwardStack and storing it
            forwardStack.push(current); //pushing the page removed from the backwardStack
            rootController.loadPage(backwardStack.peek()); //loading the new current page, the last on the backwardStack
        }
    }

    @FXML
    public void nextPage() //this method loads next pag from the forwardStack
    {
        if (!forwardStack.isEmpty()) //if the stack has at least one page
        {
            String page = forwardStack.pop(); //taking current page away from the forwardStack and storing it
            backwardStack.push(page); //pushing the page removed from the forwardStack
            rootController.loadPage(forwardStack.peek()); //loading the new current page, the last on the forwardStack
        }
    }

    @FXML
    public void reloadPage() //this method reloads the current page
    {
        rootController.loadPage(backwardStack.peek()); //loading the last page(the current one) in the backwardStack
    }
}
