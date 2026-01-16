package bankapp.progetto20242025piragine.db;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Card {

    private int idCard;
    private int userId;
    private int idAccount;
    private String panLast4;
    private java.sql.Date expired;
    private Timestamp createdAt;
    private String nickname;
    private String color;
    private boolean favourite = false;
    private BigDecimal spendingLimit;
    private boolean status = true;

    // Costruttore vuoto (necessario per DAO)
    public Card() {
    }

    // Costruttore completo (opzionale)
    public Card( int userId, int idAccount, String panLast4, String nickname, String color, BigDecimal spendingLimit) {
        this.userId = userId;
        this.idAccount = idAccount;
        this.panLast4 = panLast4;
        this.expired = Date.valueOf(LocalDate.now().plusYears(3));
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.nickname = nickname;
        this.color = color;
        this.spendingLimit = spendingLimit;

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


    public java.sql.Date getExpired() { return expired; }
    public void setExpired(java.sql.Date expired) { this.expired = expired; }


    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public boolean isFavourite() { return favourite; }
    public void setFavourite(boolean favourite) { this.favourite = favourite; }

    public BigDecimal getSpendingLimit() { return spendingLimit; }
    public void setSpendingLimit(BigDecimal spendingLimit) { this.spendingLimit = spendingLimit; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }


}
