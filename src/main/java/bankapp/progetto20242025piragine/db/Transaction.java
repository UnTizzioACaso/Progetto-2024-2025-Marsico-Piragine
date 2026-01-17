package bankapp.progetto20242025piragine.db;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {

    private int idTransaction;
    private int sender;
    private int beneficiary;
    private BigDecimal amount;
    private String note;
    private Timestamp transactionDate;
    private String status;
    private String type;
    private int usedCard;

    // 🔹 Costruttore vuoto
    public Transaction() {}

    public Transaction(int idTransaction, int sender, int beneficiary, BigDecimal amount, String note, Timestamp transactionDate, String status, String type, int usedCard)
    {
        this.idTransaction = idTransaction;
        this.sender = sender;
        this.beneficiary = beneficiary;
        this.amount = amount;
        this.note = note;
        this.transactionDate = transactionDate;
        this.status = status;
        this.type = type;
        this.usedCard = usedCard;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
