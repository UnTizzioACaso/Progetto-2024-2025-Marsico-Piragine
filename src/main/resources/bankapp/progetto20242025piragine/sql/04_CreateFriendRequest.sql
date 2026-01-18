CREATE TABLE IF NOT EXISTS Friend_Request (
    id_request INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL UNIQUE,
    requester INTEGER NOT NULL,
    beneficiary INTEGER NOT NULL,
    date DATETIME NOT NULL,
    transaction_status TEXT CHECK(transaction_status IN ('pending', 'declined', 'completed')) DEFAULT 'pending',
    FOREIGN KEY (requester) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (beneficiary) REFERENCES User(user_id) ON DELETE CASCADE
);