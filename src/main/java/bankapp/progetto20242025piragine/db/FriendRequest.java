package bankapp.progetto20242025piragine.db;

import java.time.LocalDateTime;

public class FriendRequest {
    private int idRequest;
    private int requester;
    private int beneficiary;
    private LocalDateTime dateRequest;
    private String status; // pending, declined, accepted

    public FriendRequest(int idRequest, int requester, int beneficiary, LocalDateTime dateRequest, String status) {
        this.idRequest = idRequest;
        this.requester = requester;
        this.beneficiary = beneficiary;
        this.dateRequest = dateRequest;
        this.status = status;
    }

    public FriendRequest(int requester, int beneficiary) {
        this.requester = requester;
        this.beneficiary = beneficiary;
        this.status = "pending";
        this.dateRequest = LocalDateTime.now();
    }

    // GETTER & SETTER
    public int getIdRequest() { return idRequest; }
    public void setIdRequest(int idRequest) { this.idRequest = idRequest; }

    public int getRequester() { return requester; }
    public void setRequester(int requester) { this.requester = requester; }

    public int getBeneficiary() { return beneficiary; }
    public void setBeneficiary(int beneficiary) { this.beneficiary = beneficiary; }

    public LocalDateTime getDateRequest() { return dateRequest; }
    public void setDateRequest(LocalDateTime dateRequest) { this.dateRequest = dateRequest; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

