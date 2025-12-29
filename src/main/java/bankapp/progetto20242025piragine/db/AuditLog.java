package bankapp.progetto20242025piragine.db;


import java.sql.Timestamp;

public class AuditLog {

    private int idLog;
    private Integer userId;
    private String action;
    private String details;
    private String result;     // SUCCESSO / FALLITO
    private Timestamp timestamp;

    // 🔹 Costruttore vuoto
    public AuditLog() {}

    // 🔹 Getter & Setter
    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

