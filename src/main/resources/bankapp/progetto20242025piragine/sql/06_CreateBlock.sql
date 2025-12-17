CREATE TABLE IF NOT EXISTS Block (
    id_block INTEGER PRIMARY KEY AUTOINCREMENT,
    blocker INTEGER NOT NULL,
    block INTEGER NOT NULL,
    data_blocco DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (blocker) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (block) REFERENCES User(user_id) ON DELETE CASCADE,
    UNIQUE (blocker, block),
    CHECK (blocker != block)
);