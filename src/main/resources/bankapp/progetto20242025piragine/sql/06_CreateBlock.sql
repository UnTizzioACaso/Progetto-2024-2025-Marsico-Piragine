CREATE TABLE IF NOT EXISTS Block (
    id_blocco INTEGER PRIMARY KEY AUTOINCREMENT,
    bloccante INTEGER NOT NULL,
    bloccato INTEGER NOT NULL,
    data_blocco DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (bloccante) REFERENCES User(id_utente) ON DELETE CASCADE,
    FOREIGN KEY (bloccato) REFERENCES User(id_utente) ON DELETE CASCADE,
    UNIQUE (bloccante, bloccato),
    CHECK (bloccante != bloccato)
);