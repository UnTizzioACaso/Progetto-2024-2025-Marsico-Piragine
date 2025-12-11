CREATE TABLE IF NOT EXISTS Friendship (
    id_friendship INTEGER PRIMARY KEY AUTOINCREMENT,
    user1 INTEGER,
    user2 INTEGER,
    date_friendship DATETIME default  current_timestamp,
    FOREIGN KEY (user1) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (user2) REFERENCES User(user_id) ON DELETE CASCADE,
    UNIQUE (user1, user2),
    CHECK (user1 < user2)
);