package bankapp.progetto20242025piragine.db;

public class HomeWidgetCustom {

    private int idWidget;
    private int userId;
    private String typeWidget;
    private boolean remove;
    private int row;
    private int coloumn;

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

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row= row;
    }
    public int getColoumn() {
        return coloumn;
    }
    public void setColoumn(int coloumn) {
        this.coloumn= coloumn;
    }
}
