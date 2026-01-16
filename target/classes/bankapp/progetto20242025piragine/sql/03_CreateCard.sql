CREATE TABLE IF NOT EXISTS Card (
    id_card INTEGER PRIMARY KEY AUTOINCREMENT,
    id_account INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    pan_last4 CHAR(4) NOT NULL,
    expired DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    nickname TEXT,
    color TEXT,
    favourite BOOLEAN,
    spending_limit INTEGER CHECK (spending_limit >= 0),
    status BOOLEAN CHECK ( True or False ), --true == unblocked, false ==blocked
    FOREIGN KEY (id_account) REFERENCES Bank_Account(id_account),
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
);