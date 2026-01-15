CREATE TABLE IF NOT EXISTS Transaction1 (
    id_transaction INTEGER PRIMARY KEY AUTOINCREMENT,
    sender INTEGER,
    beneficiary INTEGER,
    amount INTEGER CHECK (amount > 0),
    note TEXT,
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status TEXT CHECK(status IN ('waiting','declined','accepted')),
    type TEXT CHECK(type IN ('donation','payment')),
    used_card INTEGER,
    FOREIGN KEY (sender) REFERENCES Bank_Account(id_account) ON DELETE SET NULL,
    FOREIGN KEY (beneficiary) REFERENCES Bank_Account(id_account) ON DELETE SET NULL,
    FOREIGN KEY (used_card) REFERENCES Card(id_card) ON DELETE SET NULL
);

CREATE INDEX IF NOT EXISTS idx_transazione_mittente ON Transaction1(sender);
CREATE INDEX IF NOT EXISTS idx_transazione_destinatario ON Transaction1(beneficiary);