package bankapp.progetto20242025piragine.db;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Card {

    private int idCard;
    private int userId;
    private int idAccount;
    private String panLast4;
    private byte[] panEncrypted;
    private java.sql.Date expired;
    private byte[] cvvEncrypted;
    private Timestamp createdAt;
    private String nickname;
    private String colour;
    private boolean favourite;
    private BigDecimal spendingLimit;
    private boolean status;

    // Costruttore vuoto (necessario per DAO)
    public Card() {
    }

    // Costruttore completo (opzionale)
    public Card(int idCard, int userId, int idAccount, String panLast4, byte[] panEncrypted,
                java.sql.Date expired, byte[] cvvEncrypted, Timestamp createdAt,
                String nickname, String colour, boolean favourite, BigDecimal spendingLimit,
                boolean status) {

        this.idCard = idCard;
        this.userId = userId;
        this.idAccount = idAccount;
        this.panLast4 = panLast4;
        this.panEncrypted = panEncrypted;
        this.expired = expired;
        this.cvvEncrypted = cvvEncrypted;
        this.createdAt = createdAt;
        this.nickname = nickname;
        this.colour = colour;
        this.favourite = favourite;
        this.spendingLimit = spendingLimit;
        this.status = status;
    }

    // GETTER e SETTER
    public int getIdCard() { return idCard; }
    public void setIdCard(int idCard) { this.idCard = idCard; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getIdAccount() { return idAccount; }
    public void setIdAccount(int idAccount) { this.idAccount = idAccount; }

    public String getPanLast4() { return panLast4; }
    public void setPanLast4(String panLast4) { this.panLast4 = panLast4; }

    public byte[] getPanEncrypted() { return panEncrypted; }
    public void setPanEncrypted(byte[] panEncrypted) { this.panEncrypted = panEncrypted; }

    public java.sql.Date getExpired() { return expired; }
    public void setExpired(java.sql.Date expired) { this.expired = expired; }

    public byte[] getCvvEncrypted() { return cvvEncrypted; }
    public void setCvvEncrypted(byte[] cvvEncrypted) { this.cvvEncrypted = cvvEncrypted; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getColour() { return colour; }
    public void setColour(String colour) { this.colour = colour; }

    public boolean isFavourite() { return favourite; }
    public void setFavourite(boolean favourite) { this.favourite = favourite; }

    public BigDecimal getSpendingLimit() { return spendingLimit; }
    public void setSpendingLimit(BigDecimal spendingLimit) { this.spendingLimit = spendingLimit; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    // TO STRING (utile per debug)
    @Override
    public String toString() {
        return "Card{" +
                "idCard=" + idCard +
                ", userId=" + userId +
                ", idAccount=" + idAccount +
                ", panLast4='" + panLast4 + '\'' +
                ", expired=" + expired +
                ", nickname='" + nickname + '\'' +
                ", colour='" + colour + '\'' +
                ", favourite=" + favourite +
                ", spendingLimit=" + spendingLimit +
                ", status=" + status +
                '}';
    }
}
