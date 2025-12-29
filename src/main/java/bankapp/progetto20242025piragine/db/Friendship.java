package bankapp.progetto20242025piragine.db;

import java.time.LocalDateTime;

public class Friendship {
    private int idFriendship;
    private int user1;
    private int user2;
    private LocalDateTime dateFriendship;

    public Friendship(int idFriendship, int user1, int user2, LocalDateTime dateFriendship) {
        this.idFriendship = idFriendship;
        this.user1 = user1;
        this.user2 = user2;
        this.dateFriendship = dateFriendship;
    }

    public Friendship(int user1, int user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.dateFriendship = LocalDateTime.now();
    }

    // GETTER & SETTER
    public int getIdFriendship() { return idFriendship; }
    public void setIdFriendship(int idFriendship) { this.idFriendship = idFriendship; }

    public int getUser1() { return user1; }
    public void setUser1(int user1) { this.user1 = user1; }

    public int getUser2() { return user2; }
    public void setUser2(int user2) { this.user2 = user2; }

    public LocalDateTime getDateFriendship() { return dateFriendship; }
    public void setDateFriendship(LocalDateTime dateFriendship) { this.dateFriendship = dateFriendship; }
}
