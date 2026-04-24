package bankapp.progetto20242025piragine.db;

import java.time.LocalDateTime;

public class FriendRequest {
    private int idRequest;
    private int requester;
    private int requested;
    private LocalDateTime dateRequest;
    private String status; // pending, declined, accepted

    public FriendRequest(int idRequest, int requester, int requested, LocalDateTime dateRequest, String status) {
        this.idRequest = idRequest;
        this.requester = requester;
        this.requested = requested;
        this.dateRequest = dateRequest;
        this.status = status;
    }

    public FriendRequest(int requester, int requested) {
        this.requester = requester;
        this.requested = requested;
        this.status = "pending";
        this.dateRequest = LocalDateTime.now();
    }

    // GETTER & SETTER
    public int getIdRequest() { return idRequest; }
    public void setIdRequest(int idRequest) { this.idRequest = idRequest; }

    public int getRequester() { return requester; }
    public void setRequester(int requester) { this.requester = requester; }

    public int getRequested() { return requested; }
    public void setRequested(int requested) { this.requested = requested; }

    public LocalDateTime getDateRequest() { return dateRequest; }
    public void setDateRequest(LocalDateTime dateRequest) { this.dateRequest = dateRequest; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

