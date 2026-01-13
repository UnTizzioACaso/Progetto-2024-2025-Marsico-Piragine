package bankapp.progetto20242025piragine.controller.popup;

import bankapp.progetto20242025piragine.controller.BranchController;
import bankapp.progetto20242025piragine.db.DataSourceProvider;
import bankapp.progetto20242025piragine.db.FriendRequest;
import bankapp.progetto20242025piragine.db.FriendRequestDAO;
import bankapp.progetto20242025piragine.db.User;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class FriendshipRequestController extends BranchController {
    @FXML private TextField searchField;
    @FXML private Button searchButton;

    @FXML private ToggleButton toggleUsername;
    @FXML private ToggleButton toggleEmail;
    @FXML private ToggleButton togglePhone;

    private final ToggleGroup toggleGroup = new ToggleGroup();
    private List<User> lastFilteredUsers = Collections.emptyList();

    private static final String BG_SELECTED = "red";
    private static final String FG_SELECTED = "white";
    private static final String BG_UNSELECTED = "white";
    private static final String FG_UNSELECTED = "red";

    private String baseStyleUsername = "";
    private String baseStyleEmail = "";
    private String baseStylePhone = "";

    private enum FilterMode { USERNAME, EMAIL, PHONE }

    @FXML
    private void initialize() {
        toggleUsername.setToggleGroup(toggleGroup);
        toggleEmail.setToggleGroup(toggleGroup);
        togglePhone.setToggleGroup(toggleGroup);

        baseStyleUsername = toggleUsername.getStyle() == null ? "" : toggleUsername.getStyle();
        baseStyleEmail = toggleEmail.getStyle() == null ? "" : toggleEmail.getStyle();
        baseStylePhone = togglePhone.getStyle() == null ? "" : togglePhone.getStyle();

        Toggle current = toggleGroup.getSelectedToggle();
        if (current == null) {
            toggleGroup.selectToggle(toggleUsername);
        }
        applyToggleStylesAndPrompt(getCurrentMode());

        toggleGroup.selectedToggleProperty().addListener((obs, oldT, newT) -> {
            if (newT == null) {
                if (oldT != null) toggleGroup.selectToggle(oldT);
                return;
            }
            FilterMode mode = getCurrentMode();
            applyToggleStylesAndPrompt(mode);
            String q = searchField.getText();
            if (q != null && !q.isBlank()) lastFilteredUsers = filterUsers(q, mode);
            else lastFilteredUsers = Collections.emptyList();
        });

        ChangeListener<String> textListener = (obs, oldVal, newVal) -> {
            if (newVal == null || newVal.isBlank()) {
                lastFilteredUsers = Collections.emptyList();
                return;
            }
            lastFilteredUsers = filterUsers(newVal, getCurrentMode());
        };
        searchField.textProperty().addListener(textListener);

        searchButton.setOnAction(e -> onSearchClicked());
    }

    private void applyToggleStylesAndPrompt(FilterMode mode) {
        setToggleStyle(toggleUsername, mode == FilterMode.USERNAME);
        setToggleStyle(toggleEmail, mode == FilterMode.EMAIL);
        setToggleStyle(togglePhone, mode == FilterMode.PHONE);

        switch (mode) {
            case USERNAME -> searchField.setPromptText("Username amico");
            case EMAIL -> searchField.setPromptText("Email amico");
            case PHONE -> searchField.setPromptText("Numero di telefono amico");
        }
    }

    private void setToggleStyle(ToggleButton btn, boolean selected) {
        String base;
        if (btn == toggleUsername) base = baseStyleUsername;
        else if (btn == toggleEmail) base = baseStyleEmail;
        else base = baseStylePhone;

        String bg = selected ? BG_SELECTED : BG_UNSELECTED;
        String fg = selected ? FG_SELECTED : FG_UNSELECTED;

        btn.setStyle(upsertCssProperty(upsertCssProperty(base, "-fx-background-color", bg), "-fx-text-fill", fg));
    }

    private String upsertCssProperty(String style, String prop, String value) {
        String s = style == null ? "" : style;
        String regex = "(?i)" + Pattern.quote(prop) + "\\s*:\\s*[^;]+;";
        if (s.matches("(?s).*" + regex + ".*")) return s.replaceAll(regex, prop + ": " + value + ";");
        if (!s.isBlank() && !s.trim().endsWith(";")) s += ";";
        return (s + " " + prop + ": " + value + ";").trim();
    }

    private FilterMode getCurrentMode() {
        Toggle selected = toggleGroup.getSelectedToggle();
        if (selected == toggleEmail) return FilterMode.EMAIL;
        if (selected == togglePhone) return FilterMode.PHONE;
        return FilterMode.USERNAME;
    }

    private List<User> filterUsers(String query, FilterMode mode) {
        String q = query.trim();
        if (q.isEmpty()) return Collections.emptyList();

        String column = switch (mode) {
            case USERNAME -> "username";
            case EMAIL -> "email";
            case PHONE -> "phone_number";
        };

        String sql =
                "SELECT user_id, first_name, last_name, username, email, phone_number, theme, gender, birth_day, birth_place, cap " +
                        "FROM User WHERE " + column + " LIKE ? " +
                        (rootController != null && rootController.user != null ? "AND user_id <> ? " : "") +
                        "ORDER BY " + column + " ASC LIMIT 20";

        List<User> out = new ArrayList<>();

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + q + "%");
            if (rootController != null && rootController.user != null) ps.setInt(2, rootController.user.getUserID());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User u = new User();
                    u.setUserID(rs.getInt("user_id"));
                    u.setFirstName(rs.getString("first_name"));
                    u.setLastName(rs.getString("last_name"));
                    u.setUsername(rs.getString("username"));
                    u.setEmail(rs.getString("email"));
                    u.setPhoneNumber(rs.getString("phone_number"));
                    u.setTheme(rs.getString("theme"));
                    u.setGender(rs.getString("gender"));
                    u.setBirthDate(rs.getString("birth_day"));
                    u.setBirthPlace(rs.getString("birth_place"));
                    u.setCap(rs.getString("cap"));
                    out.add(u);
                }
            }
        } catch (SQLException e) {
            System.err.println("[FriendshipRequestController] Errore filtro utenti: " + e.getMessage());
            e.printStackTrace();
        }

        return out;
    }

    private void onSearchClicked() {
        String q = searchField.getText() == null ? "" : searchField.getText().trim();
        if (q.isEmpty()) return;

        FilterMode mode = getCurrentMode();
        List<User> filtered = filterUsers(q, mode);
        lastFilteredUsers = filtered;

        User exact = null;
        for (User u : filtered) {
            String value = switch (mode) {
                case USERNAME -> u.getUsername();
                case EMAIL -> u.getEmail();
                case PHONE -> u.getPhoneNumber();
            };
            if (value != null && value.equalsIgnoreCase(q)) {
                exact = u;
                break;
            }
        }

        if (exact == null) return;
        if (rootController == null || rootController.user == null) return;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection()) {
            FriendRequestDAO dao = new FriendRequestDAO(conn);
            FriendRequest req = new FriendRequest(
                    0,
                    rootController.user.getUserID(),
                    exact.getUserID(),
                    LocalDateTime.now(),
                    "pending"
            );
            dao.sendRequest(req);
        } catch (SQLException e) {
            System.err.println("[FriendshipRequestController] Errore invio richiesta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<User> getLastFilteredUsers() {
        return lastFilteredUsers;
    }
}
