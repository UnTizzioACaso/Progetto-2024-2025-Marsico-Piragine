CREATE TABLE IF NOT EXISTS Notify (
    id_notify INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    id_transaction INTEGER,
    id_friend_request INTEGER,
    message TEXT,
    read BOOLEAN,
    data_creation DATETIME,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (id_transaction) REFERENCES Transaction1(id_transaction) ON DELETE CASCADE,
    FOREIGN KEY (id_friend_request) REFERENCES Friendship(id_friendship) ON DELETE CASCADE,
    CHECK (
(id_transaction IS NOT NULL AND id_friend_request IS NULL) OR
(id_transaction IS NULL AND id_friend_request IS NOT NULL)
    )
);