package bankapp.progetto20242025piragine.controller.page;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.controller.component.TopbarController;
import bankapp.progetto20242025piragine.db.User;
import bankapp.progetto20242025piragine.db.UserDAO;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class Register3Controller extends RegisterController
{


    @FXML
    public void completeRegistration() throws SQLException {
        UserDAO userdao = new UserDAO();
        userdao.registerUser(user);
        rootController.loadSideBar("/bankapp/progetto20242025piragine/fxml/component/sidebar.fxml");
        rootController.loadTopBar("/bankapp/progetto20242025piragine/fxml/component/topbar.fxml");
        rootController.loadPage("/bankapp/progetto20242025piragine/fxml/page/homepage.fxml");
    }
}
