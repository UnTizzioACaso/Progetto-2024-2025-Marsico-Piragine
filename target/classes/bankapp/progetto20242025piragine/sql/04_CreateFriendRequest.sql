CREATE TABLE IF NOT EXISTS Friend_Request (
    id_request INTEGER PRIMARY KEY AUTOINCREMENT,
    requester INTEGER NOT NULL,
    requested INTEGER NOT NULL,
    date DATETIME NOT NULL,
    status TEXT CHECK(status IN ('pending', 'declined', 'accepted')) DEFAULT 'pending',
    FOREIGN KEY (requester) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (requested) REFERENCES User(user_id) ON DELETE CASCADE,
    UNIQUE(requester, requested)
    );