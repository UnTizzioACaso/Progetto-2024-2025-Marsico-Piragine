package bankapp.progetto20242025piragine.controller;

public abstract class BranchController {

    protected RootWindowController rootController; //access to RootWindow

    public void setRootController(RootWindowController rootController) //sets the access to the RootController
    {
        this.rootController = rootController;
    }

    public RootWindowController getRootController() //gives the access to the RootController
    {
        return rootController;
    }




}
