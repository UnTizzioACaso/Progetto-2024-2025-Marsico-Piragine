PRAGMA foreign_keys = ON;
CREATE TABLE IF NOT EXISTS Bank_Account (
id_account INTEGER PRIMARY KEY AUTOINCREMENT,
user_id INTEGER NOT NULL UNIQUE ,
money INTEGER CHECK (money >= 0),
currency TEXT CHECK (currency IN ('EUR')),
iban TEXT UNIQUE,
max_transfer INTEGER DEFAULT 5000 CHECK(max_transfer >= 0),
force_pin BOOLEAN DEFAULT FALSE,
check_account TEXT CHECK (check_account IN ('open', 'closed')),
FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
);