CREATE TABLE IF NOT EXISTS Friend_Request (
    id_richiesta INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL UNIQUE,
    Requester INTEGER NOT NULL,
    Beneficiary INTEGER NOT NULL,
    date DATETIME NOT NULL,
    transaction_status TEXT CHECK(transaction_status IN ('pending', 'declined', 'completed')) DEFAULT 'pending',    FOREIGN KEY (Requester) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (Beneficiary) REFERENCES User(user_id) ON DELETE CASCADE
);