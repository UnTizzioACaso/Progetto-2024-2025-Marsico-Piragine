package bankapp.progetto20242025piragine.util;

import bankapp.progetto20242025piragine.controller.ChatBotController;
import bankapp.progetto20242025piragine.controller.RootWindowController;
import bankapp.progetto20242025piragine.controller.component.NotificationSliderController;
import bankapp.progetto20242025piragine.controller.component.TopbarController;
import bankapp.progetto20242025piragine.controller.page.BankAccountSettingsPageController;
import bankapp.progetto20242025piragine.controller.page.CardPageController;
import bankapp.progetto20242025piragine.controller.page.FriendsPageController;
import bankapp.progetto20242025piragine.controller.page.HomePageController;
import bankapp.progetto20242025piragine.controller.popup.AccountPopupController;
import bankapp.progetto20242025piragine.controller.popup.CreateCardPopupController;
import bankapp.progetto20242025piragine.controller.popup.MenageCardPopupController;
import bankapp.progetto20242025piragine.model.BankAccount;
import bankapp.progetto20242025piragine.model.User;


public class CurrentSession {

    private static User loggedUser;
    private static User secondaryUser;
    private static RootWindowController rootController;
    private static TopbarController topbarController;
    private static FriendsPageController friendsPageController;
    private static HomePageController homePageController;
    private static CardPageController cardPageController;
    private static BankAccountSettingsPageController bankAccountSettingsPageController;
    private static BankAccount loggedAccount;
    private static NotificationSliderController notificationSliderController;
    private static ChatBotController chatBotController;
    private static CreateCardPopupController createCardPopupController;
    private static MenageCardPopupController menageCardPopupController;
    private static AccountPopupController accountPopupController;

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


    public static void setHomePageController(HomePageController controller) {homePageController = controller;}

    public static HomePageController getHomePageController() {return homePageController;}


    public static CardPageController getCardPageController() {return cardPageController;}

    public static void setCardPageController(CardPageController cardPageController) {CurrentSession.cardPageController = cardPageController;}


    public static BankAccountSettingsPageController getBankAccountSettingsPageController() {return bankAccountSettingsPageController;}

    public static void setBankAccountSettingsPageController(BankAccountSettingsPageController bankAccountSettingsPageController) {CurrentSession.bankAccountSettingsPageController = bankAccountSettingsPageController;}


    public static BankAccount getLoggedAccount() {return loggedAccount;}

    public static void setLoggedAccount(BankAccount loggedAccount) {CurrentSession.loggedAccount = loggedAccount;}
}
