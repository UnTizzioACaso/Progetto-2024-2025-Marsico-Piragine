package bankapp.progetto20242025piragine.db;

public class ShortcutSidebar {

    private int idCustom;
    private int userId;
    private String typeShortcut;
    private int order;   // Order1 nel DB

    // 🔹 Costruttore vuoto
    public ShortcutSidebar() {}

    // 🔹 Getter & Setter
    public int getIdCustom() {
        return idCustom;
    }

    public void setIdCustom(int idCustom) {
        this.idCustom = idCustom;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTypeShortcut() {
        return typeShortcut;
    }

    public void setTypeShortcut(String typeShortcut) {
        this.typeShortcut = typeShortcut;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}

