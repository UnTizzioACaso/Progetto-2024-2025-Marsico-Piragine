 CREATE TABLE IF NOT EXISTS Notify (
    id_notify INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    id_transaction INTEGER,
    id_friend_request INTEGER,
    message TEXT,
    read BOOLEAN,
    data_creation DATETIME,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (id_transaction) REFERENCES Bank_Transaction(id_transaction) ON DELETE CASCADE,
    FOREIGN KEY (id_friend_request) REFERENCES Friend_Request(id_request) ON DELETE CASCADE
);