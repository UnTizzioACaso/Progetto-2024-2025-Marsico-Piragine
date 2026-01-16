package bankapp.progetto20242025piragine.db;

import bankapp.progetto20242025piragine.util.IbanGenerator;

import java.math.BigDecimal;
import java.sql.SQLException;

public class BankAccount {

    private int idAccount;
    private int userId;
    private BigDecimal money = BigDecimal.valueOf(0);
    private String currency = "EUR";
    private String iban;
    private BigDecimal maxTransfer = BigDecimal.valueOf(50);
    private boolean forcePin = true;
    private String checkAccount = "open"; // open / closed

    public BankAccount(int Id) throws SQLException {
        iban = IbanGenerator.generateItalianIban();
        while(BankAccountDAO.existsByIban(iban))
        {
            iban = IbanGenerator.generateItalianIban();
        }
        this.userId = Id;;
    }

    // 🔹 Costruttore vuoto
    public BankAccount() {}

    // 🔹 Getter & Setter
    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getMaxTransfer() {
        return maxTransfer;
    }

    public void setMaxTransfer(BigDecimal maxTransfer) {
        this.maxTransfer = maxTransfer;
    }

    public boolean isForcePin() {
        return forcePin;
    }

    public void setForcePin(boolean forcePin) {
        this.forcePin = forcePin;
    }

    public String getCheckAccount() {
        return checkAccount;
    }

    public void setCheckAccount(String checkAccount) {
        this.checkAccount = checkAccount;
    }
}
