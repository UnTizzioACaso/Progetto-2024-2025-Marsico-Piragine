package bankapp.progetto20242025piragine.util;

import bankapp.progetto20242025piragine.controller.RootWindowController;
import bankapp.progetto20242025piragine.controller.component.TopbarController;
import bankapp.progetto20242025piragine.controller.page.FriendsPageController;
import bankapp.progetto20242025piragine.model.User;


public class CurrentSession {

    private static User loggedUser;
    private static User secondaryUser;
    private static RootWindowController rootController;
    private static TopbarController topbarController;
    private static FriendsPageController friendsPageController;

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }


    public static User getSecondaryUser() {
        return secondaryUser;
    }

    public static void setSecondaryUser(User user) {
        secondaryUser = user;
    }


    public static RootWindowController getRootController() {
        return rootController;
    }

    public static void setRootController(RootWindowController controller) {
        rootController = controller;
    }


    public static TopbarController getTopbarController() {
        return topbarController;
    }

    public static void setTopbarController(TopbarController controller) {
        topbarController = controller;
    }


    public static void setFriendsPageController(FriendsPageController controller) {friendsPageController = controller;}

    public static FriendsPageController getFriendsPageController() {return friendsPageController;}

    public static void reset() {
        loggedUser = null;
        secondaryUser = null;
        rootController = null;
        topbarController = null;
    }


}
