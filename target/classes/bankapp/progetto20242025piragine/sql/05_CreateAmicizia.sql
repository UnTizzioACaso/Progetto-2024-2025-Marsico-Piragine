CREATE TABLE IF NOT EXISTS Amicizia (
    id_amicizia INTEGER PRIMARY KEY AUTOINCREMENT,
    utente1 INTEGER,
    utente2 INTEGER,
    data_creazione DATETIME,
    FOREIGN KEY (utente1) REFERENCES Utente(id_utente) ON DELETE CASCADE,
    FOREIGN KEY (utente2) REFERENCES Utente(id_utente) ON DELETE CASCADE,
    UNIQUE (utente1, utente2),
    CHECK (utente1 < utente2)
);