CREATE TABLE IF NOT EXISTS Card (
    id_card INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL UNIQUE,
    id_account INTEGER NOT NULL UNIQUE ,
    pan_last4 CHAR(4) NOT NULL,
    pan_encrypted BLOB NOT NULL,
    expired DATE NOT NULL,
    cvv_encrypted BLOB NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    nickname TEXT,
    colour TEXT,
    favourite BOOLEAN,
    spending_limit DECIMAL CHECK (spending_limit >= 0),
    status BOOLEAN CHECK ( True or False ),
    FOREIGN KEY (user_id) REFERENCES Bank_Account(user_id) ON DELETE CASCADE
);