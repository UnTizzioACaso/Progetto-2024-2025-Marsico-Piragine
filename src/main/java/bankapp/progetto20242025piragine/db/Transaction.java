package bankapp.progetto20242025piragine.db;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {

    private int idTransaction;
    private Integer sender;        // id_account (può essere null)
    private Integer beneficiary;   // id_account (può essere null)
    private BigDecimal ammount;
    private String note;
    private Timestamp transactionDate;
    private String status; // send / declined
    private String type;   // recharge / bank transfer / fast send
    private Integer usedCard; // id_card (può essere null)

    // 🔹 Costruttore vuoto
    public Transaction() {}

    // 🔹 Getter & Setter
    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Integer beneficiary) {
        this.beneficiary = beneficiary;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUsedCard() {
        return usedCard;
    }

    public void setUsedCard(Integer usedCard) {
        this.usedCard = usedCard;
    }
}
