CREATE TABLE IF NOT EXISTS Friendship (
    id_friendship INTEGER PRIMARY KEY AUTOINCREMENT,
    requester INTEGER,
    requested INTEGER,
    date_friendship DATETIME default  current_timestamp,
    FOREIGN KEY (requester) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (requested) REFERENCES User(user_id) ON DELETE CASCADE,
    UNIQUE (requester, requested),
    CHECK (requester != requested)
);