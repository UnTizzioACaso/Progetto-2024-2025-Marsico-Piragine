package bankapp.progetto20242025piragine.db;

import java.sql.Timestamp;

public class Notify {

    private int idNotify;
    private Integer userId;
    private Integer idTransaction;
    private Integer idFriendRequest;
    private String message;
    private boolean read;
    private Timestamp dataCreation;

    // 🔹 Costruttore vuoto
    public Notify() {}

    // 🔹 Getter & Setter
    public int getIdNotify() {
        return idNotify;
    }

    public void setIdNotify(int idNotify) {
        this.idNotify = idNotify;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Integer getIdFriendRequest() {
        return idFriendRequest;
    }

    public void setIdFriendRequest(Integer idFriendRequest) {
        this.idFriendRequest = idFriendRequest;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Timestamp getDataCreation() {
        return dataCreation;
    }

    public void setDataCreation(Timestamp dataCreation) {
        this.dataCreation = dataCreation;
    }
}
