package bankapp.progetto20242025piragine.db;

public class BankAccount {

    private int idAccount;
    private int userId;
    private double money;
    private String currency;
    private String iban;
    private double maxTransfer;
    private boolean forcePin;
    private String checkAccount; // open / closed

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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
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

    public double getMaxTransfer() {
        return maxTransfer;
    }

    public void setMaxTransfer(double maxTransfer) {
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
