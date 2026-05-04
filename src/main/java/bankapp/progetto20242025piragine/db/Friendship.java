package bankapp.progetto20242025piragine.db;

import java.time.LocalDateTime;

public class Friendship {
    private int idFriendship;
    private int requester;
    private int requested;
    private LocalDateTime dateFriendship;

    public Friendship(int idFriendship, int requester, int requested, LocalDateTime dateFriendship) {
        this.idFriendship = idFriendship;
        this.requester = requester;
        this.requested = requested;
        this.dateFriendship = dateFriendship;
    }

    public Friendship(int requester, int requested) {
        this.requester = requester;
        this.requested = requested;
        this.dateFriendship = LocalDateTime.now();
    }

    // GETTER & SETTER
    public int getIdFriendship() { return idFriendship; }
    public void setIdFriendship(int idFriendship) { this.idFriendship = idFriendship; }


    public int getRequested() { return requested; }
    public void setRequested(int requested) { this.requested = requested; }

    public LocalDateTime getDateFriendship() { return dateFriendship; }
    public void setDateFriendship(LocalDateTime dateFriendship) { this.dateFriendship = dateFriendship; }

    public int getRequester() {
        return requester;
    }

    public void setRequester(int requester) {
        this.requester = requester;
    }
}
