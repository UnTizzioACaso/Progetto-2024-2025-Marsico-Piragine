package bankapp.progetto20242025piragine.model;

public class HomeWidgetCustom {

    private int idWidget;
    private int userId;
    private String typeWidget;
    private boolean remove = true;
    private int row = 1;
    private int column = 1;

    // 🔹 Costruttore vuoto
    public HomeWidgetCustom() {}

    public HomeWidgetCustom( int userId, String typeWidget)
    {
        this.userId = userId;
        this.typeWidget = typeWidget;
    }

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
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
}
