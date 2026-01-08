package bankapp.progetto20242025piragine.db;

public class BankAccount {

    private int idAccount;
    private int userId;
    private double money;
    private String currency;
    private String iban;
    private double maxTransfer;
    private boolean forcePin;

    // 🔹 Costruttore vuoto
    public BankAccount() {}
    // 🔹 Costruttore completo
    public BankAccount(int idAccount, int userId, double money, String currency, String iban,
                       double maxTransfer, boolean forcePin) {
        this.idAccount = idAccount;
        this.userId = userId;
        this.money = money;
        this.currency = currency;
        this.iban = iban;
        this.maxTransfer = maxTransfer;
        this.forcePin = forcePin;
    }

    // 🔹 Costruttore senza idAccount (per inserimento nuovo conto)
    public BankAccount(int userId, double money, String currency, String iban,
                       double maxTransfer, boolean forcePin) {
        this.userId = userId;
        this.money = money;
        this.currency = currency;
        this.iban = iban;
        this.maxTransfer = maxTransfer;
        this.forcePin = forcePin;
    }

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

}
