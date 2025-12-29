package bankapp.progetto20242025piragine.db;

public class HomeWidgetCustom {

    private int idWidget;
    private int userId;
    private String typeWidget;
    private String size;
    private int position;
    private boolean remove;

    // 🔹 Costruttore vuoto
    public HomeWidgetCustom() {}

    // 🔹 Getter & Setter
    public int getIdWidget() {
        return idWidget;
    }

    public void setIdWidget(int idWidget) {
        this.idWidget = idWidget;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTypeWidget() {
        return typeWidget;
    }

    public void setTypeWidget(String typeWidget) {
        this.typeWidget = typeWidget;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }
}
