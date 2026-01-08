PRAGMA foreign_keys = ON;
CREATE TABLE IF NOT EXISTS Bank_Account (
id_account INTEGER PRIMARY KEY AUTOINCREMENT,
user_id INTEGER NOT NULL UNIQUE ,
money REAL CHECK (money >= 0),
currency TEXT CHECK (currency IN ('EUR')),
iban TEXT,
max_transfer REAL DEFAULT 50 CHECK(max_transfer >= 0),
force_pin BOOLEAN DEFAULT FALSE,
 FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
);
